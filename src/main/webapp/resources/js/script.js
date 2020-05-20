"use strict"

async function uploadFile() {

    let uploadFile = document.getElementById("uploadImageForm");

    let response = await fetch("http://localhost:8080/file_upload//upload", {
        method: 'POST',
        body: new FormData(uploadFile),

    });


    if (response.ok) {
        console.log("ok");


    } else if (response.status === 409) {
        console.log("409");

    }
}