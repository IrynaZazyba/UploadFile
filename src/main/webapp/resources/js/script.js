"use strict"

async function uploadFile() {
    clearMessageDiv();

    let uploadFile = document.getElementById("uploadImageForm");

    let response = await fetch("/upload", {
        method: 'POST',
        body: new FormData(uploadFile),
    });

    let messageDiv = document.getElementById('message');

    if (response.ok) {
        let json = await response.json();
        messageDiv.insertAdjacentHTML('afterbegin', generateMessageBlock(json.message));

    } else {
        let answer = await response.json();
        messageDiv.insertAdjacentHTML('afterbegin', generateMessageBlock(answer.message));
    }
}


document.addEventListener("DOMContentLoaded", changeInputTypeFile);

function changeInputTypeFile() {
    let inputFile = document.querySelector("input[type='file']");
    inputFile.onclick = function () {
        clearMessageDiv();
    };
}


function clearMessageDiv() {
    let messageDiv = document.getElementById('message');
    messageDiv.innerHTML = "";
}

function generateMessageBlock(message) {
    return "<div>" + message + "</div>";
}