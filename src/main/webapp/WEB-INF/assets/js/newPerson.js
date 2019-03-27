/**
 * Created by Admin on 10.12.16.
 */
window.addEventListener("load", init, false);

var isFile = false;
var i = 2;


function init () {

    console.log('init');
    fileId.addEventListener("change", validationFile, false);
    addNewSkill.addEventListener("click", addSkill, false);
    fType.addEventListener("change", createLink, false);

}

function createLink () {

    var type = document.getElementById("fType").value;
    console.log(type);
    document.getElementById("getRooms").href = "classroomDownload?fType="+type;
    var link = document.getElementById("getRooms").href;
    console.log(link);
}



function validationFile() {
    var fileId = document.getElementById("fileId").value;
    console.log(fileId);
    if (fileId == "" || fileId == null) {
        isFile = false;
    } else {
        isFile = true;
    }

    if (isFile){
        document.getElementById("addFile").removeAttribute("disabled");
    } else {
        document.getElementById("addFile").setAttribute("disabled","true");
    }
}




function addSkill () {
    console.log("show/hide")
    document.getElementById('newSkill_2').style.display ='';
}

