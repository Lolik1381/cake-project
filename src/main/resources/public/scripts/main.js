const bucketName = "bucket"
let currentPage = 0
let maxPage = 0;
let lastSearchText = ""
let lastUrl = ""

async function loadProducts(searchText) {
    let url = "/products/findAll?page=0&size=21&searchText=" + searchText
    let response = await fetchAdapter(url)
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

    let selectedCheckBoxes = document.querySelectorAll('input.form-check-input:checked');
    let checkedValues = Array.from(selectedCheckBoxes).map(cb => cb.value);

    for (let i = 0; i < checkedValues.length; i++) {
        searchText += "&productTypes=" + checkedValues[i]
    }

    currentPage = 0
    await loadProducts(searchText)
}

async function addMore() {
    if (currentPage < maxPage) {
        let url = "/products/findAll?page=" + currentPage + "&size=20&searchText=" + lastSearchText
        let response = await fetchAdapter(url)

        addProducts(await response.json())
        hiddenAddMoreButtonIfLastPage()
    }
}

async function sortFilter(param, type) {
    let url = lastUrl + "&sort=" + param + "%2C" + type

    let response = await fetchAdapter(url, false)
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

addProducts = function (productJson) {
    currentPage++;
    maxPage = productJson.page.totalPages
    showAddMoreButton()

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
    if (currentPage >= maxPage) {
        document.getElementById('buttonAddMore').hidden = true
    }
}

showAddMoreButton = function () {
    if (currentPage !== maxPage) {
        document.getElementById('buttonAddMore').hidden = false
    }
}

addProductType = async function () {
    let url = window.location.origin + "/productType/all"

    let response = await fetchAdapter(url)
    if (response.ok) {
        let json = await response.json()

        for (let i = 0; i < json.length; i++) {
            document.getElementById('checkbox-container').innerHTML +=
                "<div class=\"form-check\">\n" +
                "            <input class=\"form-check-input\" type=\"checkbox\" value=\"" + json[i].id + "\" id=\"checkbox" + (i + 1) + "\">\n" +
                "            <label class=\"form-check-label\" for=\"checkbox" + (i + 1) + "\">\n" + json[i].name +
                "            </label>\n" +
                "          </div>"
        }
    }
}

fetchAdapter = function (url, adapter = true) {
    if (adapter) {
        lastUrl = url
    }

    return fetch(url)
}

function clearProducts() {
    document.getElementById('products').innerHTML = null;
}

document.addEventListener("DOMContentLoaded", function() {
    if (localStorage.getItem(bucketName) != null) {
        let size = JSON.parse(localStorage.getItem(bucketName)).length
        document.getElementById("bucket").innerHTML = "КОРЗИНА (" + size + ")"
    }

    document.getElementById("catalog_li").classList.add("active");

    loadProducts("")
    addProductType()
});