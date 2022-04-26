function alerted() {
    let login = document.getElementById("login").value;
    let pas = document.getElementById("password").value;
    let xhr = new XMLHttpRequest();
    xhr.open('POST', 'UserServlet', false);
    let mas = [login, pas];
    xhr.send(mas);
    if (xhr.status === 200) {
        let res = xhr.responseText.trim();
        console.log(res.length);
        if (res == 0){
            console.log(xhr.responseText);
            alert("Логин/пароль не верен")
        }
        else {
            document.location.href = "artist.jsp";
        }
    } else {
        alert("Ошибка - " + xhr.status);
    }
}

let prevId;

function getSongInfo(id){
    if(prevId != null)
        document.getElementById(prevId).style.visibility = "hidden";
    prevId = id;
   document.getElementById(id).style.visibility = "visible";
}


