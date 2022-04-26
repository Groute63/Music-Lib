function addSong(){
    var object = {};
    var formData = new FormData(document.forms.form);
    object.name = formData.get("name");
    object.length =  parseInt(formData.get("lengthmin"));
    var json = JSON.stringify(object);
    var xhr = new XMLHttpRequest();
    xhr.open("POST", '/song-management/song', true)
    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    xhr.send(json);
}

function deleteSong(){
    var formData = new FormData(document.forms.form);
    var id = formData.get("id");
    var xhr = new XMLHttpRequest();
    xhr.open("DELETE", '/song-management/song/'+id, true)
    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    xhr.send();
}

function updateSong(){
    var object = {};
    var formData = new FormData(document.forms.form);
    object.name = formData.get("name");
    object.length =  parseInt(formData.get("lengthmin"));
    object.id = formData.get("id");
    var json = JSON.stringify(object);
    var xhr = new XMLHttpRequest();
    xhr.open("PUT", '/song-management/song', true)
    xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
    xhr.send(json);
}