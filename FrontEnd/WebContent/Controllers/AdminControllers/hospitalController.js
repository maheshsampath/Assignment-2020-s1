var $username = "";
var $pw = "";
var Hospital = {
    name: "",
    address: "",
    tp: ""
};
var Hospitals = [];
var hosptltable = ["name", "address", "tp"];

var $rootUrl = "http://localhost:8080/demorest/webapi/adminlogin/hospital/";
var $globalUrl = "";

$(document).ready(function() {
    $username = sessionStorage.getItem("username");
    $pw = sessionStorage.getItem("pw");

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
            var celledit = document.createElement("td");
            celledit.innerHTML = "<a href='#editModal' onclick='editbuttonClick(" + userSelect + ")' class='edit' data-toggle='modal'><i class='material-icons' data-toggle='tooltip' title='Edit'>&#xE254;</i></a>";
            row.appendChild(celledit);
            var celldelete = document.createElement("td");
            celldelete.innerHTML = "<a href='#deleteModal' onclick='deletebuttonClick(" + userSelect + ")' class='delete' data-toggle='modal'><i class='material-icons' data-toggle='tooltip' title='Delete'>&#xE872;</i></a>";
            row.appendChild(celldelete);
            tbody.appendChild(row);
        });
    }

}

function setEditData() {
    Hospital.name = document.getElementById("nameEdit").value;
    Hospital.tp = document.getElementById("tpEdit").value;
    Hospital.address = document.getElementById("addressEdit").value;
}



function addHosptl() {
    Doctors.forEach(function(doc1) {
        var hsptlid = doc1["hospitalId"];
        Hospitals.forEach(function(hsptl) {
            if (hsptl["id"] == hsptlid) {
                doc1["hospitalId"] = hsptl["name"];
            }
        });
        Docs1.push(doc1);
    });
}

function signOut() {
    sessionStorage.clear();
    window.location = "/FrontEnd/views/login.jsp";
}

$(document).on("click", "#sidebarCollapse", function(event) {
    $('#sidebar').toggleClass('active');
});

function removetble() {
    var myTable = document.getElementById('listtable');
    var rowCount = myTable.rows.length;
    for (var x = rowCount - 1; x > 0; x--) {
        myTable.deleteRow(x);
    }

}
$(document).on("click", "#CloseBtn", function(event) {
    window.location.reload();
});

}