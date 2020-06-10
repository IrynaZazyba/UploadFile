"use strict"

async function uploadFile() {

    let errorMessageDiv=document.getElementById('errorMessage');
    errorMessageDiv.innerHTML="";

    let uploadFile = document.getElementById("uploadImageForm");

    let response = await fetch("/file_upload/upload", {
        method: 'POST',
        body: new FormData(uploadFile),
    });

    if (response.ok) {
        let json = await response.json();
        errorMessageDiv.insertAdjacentHTML('afterbegin', generateMessageBlock(json.message));

    } else {
        let answer = await response.json();
        errorMessageDiv.insertAdjacentHTML('afterbegin', generateMessageBlock(answer.message));
    }
}


function generateMessageBlock(message) {
    return "<div>" + message + "</div>";
}