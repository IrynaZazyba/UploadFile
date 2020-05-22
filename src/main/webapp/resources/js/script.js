"use strict"

async function uploadFile() {

    let uploadFile = document.getElementById("uploadImageForm");

    let response = await fetch("http://localhost:8080/file_upload/upload", {
        method: 'POST',
        body: new FormData(uploadFile),
    });

    if (response.ok) {
        let json = await response.json();
        document.getElementById('uploadImageForm').insertAdjacentHTML('beforebegin', generateMessageBlock(json.message));

    } else {
        let answer = await response.json();
        document.getElementById('uploadImageForm').insertAdjacentHTML('beforebegin', generateMessageBlock(answer.message));
    }
}


function generateMessageBlock(message) {
    return "<div>" + message + "</div>";
}