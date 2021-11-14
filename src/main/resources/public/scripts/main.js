let currentPage = 0
let maxPage = 0;
let lastSearchText = ""

async function loadProducts(searchText) {
    let url = "/products/findAll?page=0&size=21&searchText=" + searchText
    let response = await fetch(url)
    lastSearchText = searchText

    if (response.ok) {
        clearProducts()

        let productJson = await response.json()
        addProducts(productJson)

        hiddenAddMoreButtonIfLastPage()
        console.log(productJson)
    } else {
        alert("Ошибка HTTP: " + response.status)
    }
}

async function search() {
    let searchText = document.getElementById("inputSearch").value

    currentPage = 0
    await loadProducts(searchText)
}

async function addMore() {
    if (currentPage < maxPage) {
        let url = "/products/findAll?page=" + currentPage + "&size=20&searchText=" + lastSearchText
        let response = await fetch(url)

        addProducts(await response.json())
        hiddenAddMoreButtonIfLastPage()
    }
}

addProducts = function (productJson) {
    currentPage++;
    maxPage = productJson.page.totalPages
    showAddMoreButton()
    console.log(currentPage)
    console.log(maxPage)

    for (let i = 0; i < productJson.content.length; i++) {
        let urlImage = window.location.origin + "/file/" + productJson.content[i].file.id
        let urlProduct = window.location.origin + "/product/index/" + productJson.content[i].id

        document.getElementById('products').innerHTML +=
            "     <a href=\"" + urlProduct + "\" class=\"a-style\">\n" +
            "        <div class=\"col\">\n" +
            "          <div class=\"card h-100 card-border-content-fix\">\n" +
            "            <img src=\"" + urlImage + "\" class=\"card-img-top img-card\">\n" +
            "            <div class=\"card-body\">\n" +
            "              <h5 class=\"card-title\">" + productJson.content[i].name + "</h5>\n" +
            "              <p class=\"card-text\">" + productJson.content[i].cost + " Р</p>\n" +
            "            </div>\n" +
            "          </div>\n" +
            "        </div>\n" +
            "      </a>"
    }
}

hiddenAddMoreButtonIfLastPage = function () {
    if (currentPage === maxPage) {
        document.getElementById('buttonAddMore').hidden = true
    }
}

showAddMoreButton = function () {
    if (currentPage !== maxPage) {
        document.getElementById('buttonAddMore').hidden = false
    }
}

function clearProducts() {
    document.getElementById('products').innerHTML = null;
}

document.addEventListener("DOMContentLoaded", function() {
    loadProducts("");
});