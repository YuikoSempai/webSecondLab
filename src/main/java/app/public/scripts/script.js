let form = document.querySelector('.form');
form.addEventListener('submit', getFromValue);
let validationMessage = document.querySelector('.validation-message');

function getFromValue(event) {
    event.preventDefault();
    const data = new FormData(form);

    let x = data.get("checkX");
    let y = data.get("y");
    let R = data.get("radius");
    let secretMessage = data.get("secret");
    if (!isNaN(x) && !isNaN(y) && !isNaN(R)) {
        if (x === "" || y === "" || R === "") {
            validationMessage.textContent = "Not all data entered.";
            return;
        }
        sendData(x, y, R,secretMessage);

    } else {
        validationMessage.textContent = 'Error in the input parameters. Incorrect characters were entered';
    }
}

document.querySelector('.clear-button').onclick = function () {
    let xhr = new XMLHttpRequest();
    xhr.open("DELETE", "/clear", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send();
    xhr.onreadystatechange = function () {
        window.location.reload();
    }
}

function onlyOne(checkbox) {
    let checkboxes = document.getElementsByName('check')
    checkboxes.forEach((item) => {
        if (item !== checkbox) item.checked = false
    })
}

function draw() { // drawing grid
    let canvas = document.getElementById('image');
    if (canvas.getContext) {
        let ctx = canvas.getContext('2d');

        ctx.fillStyle = "rgba(256, 256, 256, 0.8)"; // background fill
        ctx.fillRect(0, 0, canvas.width, canvas.height);

        for (let x = 40; x < 361; x += 40) { // gird
            ctx.moveTo(x, 0);
            ctx.lineTo(x, 400);
        }
        for (let y = 40; y < 361; y += 40) {
            ctx.moveTo(0, y);
            ctx.lineTo(400, y);
        }
        ctx.strokeStyle = "#333";
        ctx.stroke();

        ctx.fillStyle = 'black'; //axis
        ctx.lineWidth = 2;
        ctx.beginPath();
        ctx.moveTo(0, 200);
        ctx.lineTo(400, 200);
        ctx.moveTo(200, 0);
        ctx.lineTo(200, 400);
        ctx.stroke();
        getAllElements(3)
    }
}

let input;
let parameter;
let flag = false;
addEventListenerToCanvas();

function setInput(id) {//set input from <radio>
    input = document.getElementById(id);
    drawGraphic();
}

function drawGraphic() {//draw Graphic with input radius
    parameter = input.value.replace(',', '.');
    let canvas = document.getElementById('image');
    if (canvas.getContext) {
        let ctx = canvas.getContext('2d');
        ctx.clearRect(0, 0, canvas.width, canvas.height);
        ctx.fillStyle = "rgba(256, 256, 256, 0.8)"; // background fill

        ctx.fillStyle = 'rgb(35, 184, 253)'; //area

        //circle
        ctx.moveTo(200, 200);
        ctx.arc(200, 200, parameter * 40, 0, Math.PI / 2);
        ctx.fill();

        //rectangle
        ctx.fillRect(200 - parameter * 40, 200 - parameter * 20, parameter * 40, parameter * 20);

        //triangle
        ctx.moveTo(200, 200 - parameter * 40);
        ctx.lineTo(200 + parameter * 20, 200);
        ctx.lineTo(200, 200);
        ctx.fill();

        for (let x = 40; x < 361; x += 40) { // gird
            ctx.moveTo(x, 0);
            ctx.lineTo(x, 400);
        }
        for (let y = 40; y < 361; y += 40) {
            ctx.moveTo(0, y);
            ctx.lineTo(400, y);
        }
        ctx.strokeStyle = "#333";
        ctx.stroke();
        ctx.beginPath();
        getAllElements(parameter);
    }
}

function getAndSendCursorPosition(canvas, event) {
    if (document.getElementById('1Radius').checked || document.getElementById('2Radius').checked || document.getElementById('3Radius').checked || document.getElementById('4Radius').checked || document.getElementById('5Radius').checked) {
        const rect = canvas.getBoundingClientRect()
        let x = rounded((-1) * (150 - (event.clientX - rect.left)) / 30);
        let y = rounded((150 - (event.clientY - rect.top)) / 30);
        const data = new FormData(form);
        const R = data.get("radius");
        const secretMessage = data.get("secret");
        sendData(x, y, R, secretMessage);
    } else {
        alert("Select radius first")
    }
}

var rounded = function (number) {
    return +number.toFixed(2);
}


function addEventListenerToCanvas() {
    const canvas = document.querySelector('canvas');
    canvas.addEventListener('mousedown', function (e) {
        getAndSendCursorPosition(canvas, e);
    })
}

function sendData(x, y, R,secretWord) {
    if (y < 3 && y > -3 && x > -5 && x < 3 && R >= 1 && R <= 5) {
        validationMessage.textContent = "The data has been verified!"
        let data = "R=" + encodeURIComponent(R) + "&x=" + encodeURIComponent(x) + "&y=" + encodeURIComponent(y);
        let xhr = new XMLHttpRequest();
        xhr.open("POST", "/control", true);
        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        xhr.setRequestHeader("Authorization", "Bearer "+secretWord);
        xhr.send(data);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    let checkBox = document.querySelector('.uploadCheckBox');
                    if (checkBox.checked) {
                        window.location.replace("/result.jsp");
                    }
                    validationMessage.textContent = validationMessage.textContent + " ... Добавляю в таблицу ...";
                    let table = document.querySelector('.table').getElementsByTagName('tbody')[0];
                    let newRow = table.insertRow();
                    let newRadiusCell = newRow.insertCell();
                    let newXCell = newRow.insertCell();
                    let newYCell = newRow.insertCell();
                    let newStatusCell = newRow.insertCell();
                    newXCell.append(x);
                    newYCell.append(y);
                    newRadiusCell.append(R);
                    console.log(xhr.response);
                    if (xhr.response) {
                        newStatusCell.append("Попала");
                    } else {
                        newStatusCell.append("Мимо");
                    }
                    drawDot(x, y, R, xhr.response);
                    console.log(xhr.response);
                }
            }
        }
    } else {
        validationMessage.textContent = "Error in the input parameters. Y does not belong to (-3;3)";
    }
}

function drawDot(x, y, R, status) {
    console.log("draw");
    let canvas = document.getElementById('image');
    let ctx = canvas.getContext('2d');
    let circle = new Path2D();
    circle.moveTo(200 + x * 40, 200 - y * 40);
    circle.arc(200 + x * 40, 200 - y * 40, 10, 0, 2 * Math.PI)
    if (status) {
        //green
        ctx.fillStyle = 'green';
    } else {
        //black
        ctx.fillStyle = 'red';
    }
    ctx.fill(circle);
}

function getAllElements(startR) {
    let xhr = new XMLHttpRequest();
    xhr.open("GET", "/control", true);
    xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    xhr.send();
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4) {
            if (xhr.status === 200) {
                let array = xhr.response.split('\n');
                console.log(array)
                for(let i =0;i<array.length-1;i++){
                    console.log(array[i].split(' '))
                    let arrayWithStatus = array[i].split(' ')
                    let x = arrayWithStatus[0].match(/-?[0-9].*/);
                    let y = arrayWithStatus[1].match(/-?[0-9].*/)
                    let R = arrayWithStatus[2].match(/-?[0-9].*/)
                    let status = arrayWithStatus[3].match(/true/)
                    if(startR == R ) {
                        drawDot(x, y, R, status)
                    }
                }
            }
        }
    }
}