async function loadProductType() {
    let url = "/productType/all"
    let response = await fetch(url)

    if (response.ok) {
        let json = await response.json()

        for (let i = 0; i < json.length; i++) {
            document.getElementById('dropdown-menu').innerHTML +=
                "<li><button type=\"button\" class=\"dropdown-item\" onclick=\"dropdown('" + json[i].name + "')\">" + json[i].name + "</button></li>"
        }
    }
}

async function productForm() {
    console.log("SAVE!")
    let origin = window.location.origin
    let file = document.getElementById("file").files[0]
    let data = new FormData()
    data.append("file", file)

    let fileResponse = await fetch(origin + "/file/upload", {
        method: 'POST',
        body: data
    });
    let jsonFile = await fileResponse.json();

    let productTypeName = document.getElementById('inputProductType').value;
    let productTypeResponse = await fetch(origin + "/productType/findByName?name=" + productTypeName);

    if (productTypeResponse === null) {
        productTypeResponse = await fetch(origin + "/productType/create", {
            method: 'POST',
            body: {"name": productTypeName}
        })
    }
    let jsonProductType = await productTypeResponse.json();

    let name = document.getElementById('inputName').value;
    let cost = document.getElementById('inputCost').value;
    let description = document.getElementById('inputDescription').value;
    let composition = document.getElementById('inputComposition').value;
    let worth = document.getElementById('inputWorth').value;
    let weight = document.getElementById('inputWeight').value;
    await fetch(origin + "/products/create", {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            "name": name,
            "cost": cost,
            "description": description,
            "composition": composition,
            "worth": worth,
            "weight": weight,
            "file": {"id": jsonFile.id},
            "productType": {"id": jsonProductType.id}
        })
    })
}

function dropdown(name) {
    document.getElementById('inputProductType').value = name
}

document.addEventListener("DOMContentLoaded", function() {
    loadProductType()
});