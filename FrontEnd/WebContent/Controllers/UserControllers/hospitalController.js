var $username = "";
var $pw = "";
var $id = "";
var Hospital = {
    name: "",
    address: "",
    tp: ""
};
var appointment = {
	    doctorId: 0,
	    userId: 0,
	    hospitalId: 0,
	    date: "",
	    paid: "no"
	};
var Hospitals = [];
var docts = [];
var hosptlId="";
var docId="";
var Searchusers = [];
var hosptltable = ["name", "address", "tp"];

var $rootUrl = "http://localhost:8080/demorest/webapi/userlogin/hospital/";
var $globalUrl = "";

$(document).ready(function() {
    $username = sessionStorage.getItem("username");
    $pw = sessionStorage.getItem("pw");
    $id = sessionStorage.getItem("id");

    $.ajax({
        url: $rootUrl,
        headers: {
            "Authorization": "Basic " + btoa($username + ":" + $pw)
        },
        contentType: 'application/json',
        dataType: 'json',
        type: 'GET',
        success: function(data) {
            Hospitals = data;
            tableCreation(Hospitals);

        }
    });


});
$(document).on("click", "#ResetBtn", function() {
    removetble();
    tableCreation(Hospitals);
});

$(document).on("click", "#searchBtn", function() {
    Searchusers = [];
    removetble();
    var searchinput = document.getElementById("inputSearch").value;
    document.getElementById("inputSearch").value = '';
    Hospitals.forEach(function(item) {
        if (item["name"] == searchinput) {
            Searchusers.push(item)
        }
    });
    tableCreation(Searchusers);

});

function tableCreation(para) {
    if (document.getElementById("listtable") != null) {
        var table = document.getElementById("listtable");
        var tbody = document.createElement("tbody");
        table.appendChild(tbody);
        para.forEach(function(item) {
            userSelect = item["id"];
            var row = document.createElement("tr");
            hosptltable.forEach(function(key) {
                var cell = document.createElement("td");
                cell.textContent = item[key];
                row.appendChild(cell);
            });
            var cellview = document.createElement("td");
            cellview.innerHTML = "<a href='#viewModal' onclick='ViewbuttonClick(" + userSelect + ")' class='view' data-toggle='modal'><i class='material-icons' data-toggle='tooltip' title='Edit'>visibility</i></a>";
            row.appendChild(cellview);
            tbody.appendChild(row);
        });
    }

}
function signOut() {
    sessionStorage.clear();
    window.location = "/FrontEnd/views/login.jsp";
}

$(document).on("click", "#sidebarCollapse", function(event) {
    $('#sidebar').toggleClass('active');
});

$(document).on("click", "#CloseBtn1", function(event) {
    window.location.reload();
});
$(document).on("click", "#CloseBtn2", function(event) {
    window.location.reload();
});