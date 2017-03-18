var tabBody = document.getElementsByTagName("tbody").item(0);

window.onload = function (e) {
    fetchData("Person");
};

var deletePerson = function(){
    var tbl = $('#persons tr:has(td)').map(function (i, v) {
        var $td = $('td', this);
        return {
            id: $td.eq(0).text(),
            
        };
    }).get();
    var url = "http://localhost:8084/CA2/api/Person/"+tbl[0].id;
    fetch(url, {method: "DELETE"}).then(function(res){ return res.json(); })
    .then(function(data){ fetchData('Person') })
}

var editPerson = function () {
    saveRow();
    var tbl = $('#persons tr:has(td)').map(function (i, v) {
        var $td = $('td', this);
        return {
            id: $td.eq(0).text(),
            firstName: $td.eq(1).text(),
            lastName: $td.eq(2).text(),
            email: $td.eq(3).text(),
            street: $td.eq(4).text(),
            additionalInfo: $td.eq(5).text(),
            hDescription: $td.eq(6).text(),
            name: $td.eq(7).text(),
            pDescription: $td.eq(8).text(),
            number: $td.eq(9).text(),
            city: $td.eq(10).text(),
            zip: $td.eq(11).text()
        };
    }).get();
    var t = tbl[0];
    var person1 = {
        "id": t.id,
        "firstName": t.firstName,
        "lastName": t.lastName,
        "email": t.email,
        "address": {
            "street": t.street,
            "additionalInfo": t.additionalInfo,
            "cityInfo": {
                "city": t.city,
                "zip": t.zip
            }
        },
        "hobbies": [
            {
                "description": t.hDescription,
                "name": t.name
            }
        ],
        "phones": [
            {
                "description": t.pDescription,
                "number": t.number
            }
        ]
    };
    var data = JSON.stringify(person1);
    var url = "http://localhost:8084/CA2/api/Person";
    fetch(url, {method: "PUT", body: data})
    .then(function(res){ return res.json(); })
    .then(function(data){ fetchData('Person') })
            
    ;
//    fetchData('Person');
};

var fetchData = function (urli) {
    var url = "http://localhost:8084/CA2/api/" + urli;
    var promise = fetch(url, {method: 'get'});
    promise.then(function (response) {
        return response.text();
    }).then(function (text) {
        createJsonArray(text);
    });
};

var createJsonArray = function (text) {
    document.getElementById("btn").style.visibility = "hidden";
    document.getElementById("btn1").style.visibility = "hidden";
    document.getElementById("btn2").style.visibility = "hidden";
    var personArray = JSON.parse(text);
    if (personArray.length === undefined) {
        personArray = [JSON.parse(text)];
        document.getElementById("btn").style.visibility = "visible";
        document.getElementById("btn1").style.visibility = "visible";
        document.getElementById("btn2").style.visibility = "visible";
        populateTable(personArray);
        editRow();
    } else if (personArray.length > 0) {
        populateTable(personArray,1);
    }
};

var editRow = function () {
    document.getElementById("firstName").innerHTML = "<input type='text' id='iFirstName' value='" + document.getElementById("firstName").innerHTML + "'>";
    document.getElementById("lastName").innerHTML = "<input type='text' id='iLastName' value='" + document.getElementById("lastName").innerHTML + "'>";
    document.getElementById("email").innerHTML = "<input type='text' id='iEmail' value='" + document.getElementById("email").innerHTML + "'>";
    document.getElementById("street").innerHTML = "<input type='text' id='iStreet' value='" + document.getElementById("street").innerHTML + "'>";
    document.getElementById("additionalInfo").innerHTML = "<input type='text' id='IadditionalInfo' value='" + document.getElementById("additionalInfo").innerHTML + "'>";
    document.getElementById("hDescription").innerHTML = "<input type='text' id='ihDescription' value='" + document.getElementById("hDescription").innerHTML + "'>";
    document.getElementById("name").innerHTML = "<input type='text' id='iName' value='" + document.getElementById("name").innerHTML + "'>";
    document.getElementById("pDescription").innerHTML = "<input type='text' id='ipDescription' value='" + document.getElementById("pDescription").innerHTML + "'>";
    document.getElementById("number").innerHTML = "<input type='text' id='iNumber' value='" + document.getElementById("number").innerHTML + "'>";
    document.getElementById("city").innerHTML = "<input type='text' id='iCity' value='" + document.getElementById("city").innerHTML + "'>";
    document.getElementById("zip").innerHTML = "<input type='text' id='iZip' value='" + document.getElementById("zip").innerHTML + "'>";
};

var saveRow = function () {
    document.getElementById("firstName").innerHTML= document.getElementById("iFirstName").value;
    document.getElementById("lastName").innerHTML= document.getElementById("iLastName").value;
    document.getElementById("email").innerHTML= document.getElementById("iEmail").value;
    document.getElementById("street").innerHTML= document.getElementById("iStreet").value;
    document.getElementById("additionalInfo").innerHTML= document.getElementById("IadditionalInfo").value;
    document.getElementById("hDescription").innerHTML= document.getElementById("ihDescription").value;
    document.getElementById("name").innerHTML= document.getElementById("iName").value;
    document.getElementById("pDescription").innerHTML= document.getElementById("ipDescription").value;
    document.getElementById("number").innerHTML= document.getElementById("iNumber").value;
    document.getElementById("city").innerHTML= document.getElementById("iCity").value;
    document.getElementById("zip").innerHTML= document.getElementById("iZip").value;
};

var populateTable = function (personArray,bool) {
    tabBody.innerHTML = null;
    createHeaders();
    for (var i = 0; i < personArray.length; i++) {
        row = document.createElement("tr");
        cell1 = document.createElement("td");
        cell2 = document.createElement("td");
        cell3 = document.createElement("td");
        cell4 = document.createElement("td");
        cell5 = document.createElement("td");
        cell6 = document.createElement("td");
        cell7 = document.createElement("td");
        cell8 = document.createElement("td");
        cell9 = document.createElement("td");
        cell10 = document.createElement("td");
        cell11 = document.createElement("td");
        cell12 = document.createElement("td");
        textnode1 = document.createTextNode(personArray[i].id);
        textnode2 = document.createTextNode(personArray[i].firstName);
        textnode3 = document.createTextNode(personArray[i].lastName);
        textnode4 = document.createTextNode(personArray[i].email);
        textnode5 = document.createTextNode(personArray[i].address.street);
        textnode6 = document.createTextNode(personArray[i].address.additionalInfo);
        textnode7 = document.createTextNode(personArray[i].hobbies[0].description);
        textnode8 = document.createTextNode(personArray[i].hobbies[0].name);
        textnode9 = document.createTextNode(personArray[i].phones[0].description);
        textnode10 = document.createTextNode(personArray[i].phones[0].number);
        textnode11 = document.createTextNode(personArray[i].cityInfo.city);
        textnode12 = document.createTextNode(personArray[i].cityInfo.zip);
        cell1.appendChild(textnode1);
        if(bool===1){
        row.setAttribute('onclick', "fetchData(\"Person/" + personArray[i].id + "\")");}
        cell2.appendChild(textnode2);
        cell2.setAttribute('id', 'firstName');
        cell3.appendChild(textnode3);
        cell3.setAttribute('id', 'lastName');
        cell4.appendChild(textnode4);
        cell4.setAttribute('id', 'email');
        cell5.appendChild(textnode5);
        cell5.setAttribute('id', 'street');
        cell6.appendChild(textnode6);
        cell6.setAttribute('id', 'additionalInfo');
        cell7.appendChild(textnode7);
        cell7.setAttribute('id', 'hDescription');
        cell8.appendChild(textnode8);
        cell8.setAttribute('id', 'name');
        cell9.appendChild(textnode9);
        cell9.setAttribute('id', 'pDescription');
        cell10.appendChild(textnode10);
        cell10.setAttribute('id', 'number');
        cell11.appendChild(textnode11);
        cell11.setAttribute('id', 'city');
        cell12.appendChild(textnode12);
        cell12.setAttribute('id', 'zip');
        row.appendChild(cell1);
        row.appendChild(cell2);
        row.appendChild(cell3);
        row.appendChild(cell4);
        row.appendChild(cell5);
        row.appendChild(cell6);
        row.appendChild(cell7);
        row.appendChild(cell8);
        row.appendChild(cell9);
        row.appendChild(cell10);
        row.appendChild(cell11);
        row.appendChild(cell12);
        tabBody.appendChild(row);
    }
};

var createHeaders = function () {
    row = document.createElement("tr");
    cell1 = document.createElement("th");
    cell2 = document.createElement("th");
    cell3 = document.createElement("th");
    cell4 = document.createElement("th");
    cell5 = document.createElement("th");
    cell6 = document.createElement("th");
    cell7 = document.createElement("th");
    cell8 = document.createElement("th");
    cell9 = document.createElement("th");
    cell10 = document.createElement("th");
    cell11 = document.createElement("th");
    cell12 = document.createElement("th");
    textnode1 = document.createTextNode("ID");
    textnode2 = document.createTextNode("Firstname");
    textnode3 = document.createTextNode("Lastname");
    textnode4 = document.createTextNode("Email");
    textnode5 = document.createTextNode("Street Address");
    textnode6 = document.createTextNode("Description");
    textnode7 = document.createTextNode("Hobby");
    textnode8 = document.createTextNode("Description");
    textnode9 = document.createTextNode("Phone type");
    textnode10 = document.createTextNode("Number");
    textnode11 = document.createTextNode("City");
    textnode12 = document.createTextNode("Zip");
    cell1.appendChild(textnode1);
    cell2.appendChild(textnode2);
    cell3.appendChild(textnode3);
    cell4.appendChild(textnode4);
    cell5.appendChild(textnode5);
    cell6.appendChild(textnode6);
    cell7.appendChild(textnode7);
    cell8.appendChild(textnode8);
    cell9.appendChild(textnode9);
    cell10.appendChild(textnode10);
    cell11.appendChild(textnode11);
    cell12.appendChild(textnode12);
    row.appendChild(cell1);
    row.appendChild(cell2);
    row.appendChild(cell3);
    row.appendChild(cell4);
    row.appendChild(cell5);
    row.appendChild(cell6);
    row.appendChild(cell7);
    row.appendChild(cell8);
    row.appendChild(cell9);
    row.appendChild(cell10);
    row.appendChild(cell11);
    row.appendChild(cell12);
    tabBody.appendChild(row);
};