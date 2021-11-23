let myModal
let modelMessageInDocument

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
    try {
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
        let response = await fetch(origin + "/products/create", {
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

        if (!response.ok) {
            throw new Error("Произошла ошибка.\n Продукт не добавлен!")
        }

        modelMessageInDocument.innerHTML = "Продукт успешно добален!";
        myModal.show()

        document.getElementById('inputName').innerHTML = "";
        document.getElementById('inputCost').innerHTML = "";
        document.getElementById('inputDescription').innerHTML = "";
        document.getElementById('inputComposition').innerHTML = "";
        document.getElementById('inputWorth').innerHTML = "";
        document.getElementById('inputWeight').innerHTML = "";

    } catch (e) {
        modelMessageInDocument.innerHTML = e.message
        myModal.show()
    }
}

function dropdown(name) {
    document.getElementById('inputProductType').value = name
}

document.addEventListener("DOMContentLoaded", function() {
    myModal = new bootstrap.Modal(document.getElementById('exampleModal'), {})
    modelMessageInDocument = document.getElementById("model-message")

    loadProductType()
});