//get text 1 by ajax

async function loadProducts(searchText) {
    let url = "/products/findAll?page=0&size=50&searchText=" + searchText
    let response = await fetch(url)

    if (response.ok) {
        let productJson = await response.json()
        console.log(productJson)

        clearProducts()
        for (let i = 0; i < productJson.content.length; i++) {
            let urlImage = window.location.origin + "/file/" + productJson.content[i].file.id
            let urlProduct = window.location.origin + "/product/index/" + productJson.content[i].id

            document.getElementById('products').innerHTML +=
                "     <a href=\"" + urlProduct + "\" class=\"a-style\">\n" +
                "        <div class=\"col\">\n" +
                "          <div class=\"card h-100\">\n" +
                "            <img src=\"" + urlImage + "\" class=\"card-img-top img-card\">\n" +
                "            <div class=\"card-body\">\n" +
                "              <h5 class=\"card-title\">" + productJson.content[i].name + "</h5>\n" +
                "              <p class=\"card-text\">" + productJson.content[i].cost + " Р</p>\n" +
                "            </div>\n" +
                "          </div>\n" +
                "        </div>\n" +
                "      </a>"
        }
    } else {
        alert("Ошибка HTTP: " + response.status)
    }
}

async function search() {
    let searchText = document.getElementById("inputSearch").value
    loadProducts(searchText)
}

function clearProducts() {
    document.getElementById('products').innerHTML = null;
}

async function updphoto() {
    var file = document.getElementById("file").files[0];
    let data = new FormData();
    data.append("file", file);

    await fetch("/file/upload", {
        method: 'POST',
        body: data
    });
}

document.addEventListener("DOMContentLoaded", function() {
    loadProducts("");
});