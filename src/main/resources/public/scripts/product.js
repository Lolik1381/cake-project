const bucketName = "bucket"

async function loadProducts() {
    let location = window.location.href

    let split = location.split("/")
    let productId = split[split.length - 1]

    let response = await fetch("/products/" + productId)
    if (response.ok) {
        let data = await response.json()

        console.log(data)

        document.getElementById("id").innerHTML = data.id
        document.getElementById("name").innerHTML = data.name
        document.getElementById("cost").innerHTML = data.cost + " Р"
        document.getElementById("description").innerHTML = data.description
        document.getElementById("weight").innerHTML = "Вес: " + data.weight + " кг"
        document.getElementById("worth").innerHTML = data.worth
        document.getElementById("composition").innerHTML = data.composition

        let urlImage = window.location.origin + "/file/" + data.file.id
        document.getElementById("img").innerHTML =
            "<img src=\"" + urlImage + "\" class=\"img-card\"/>"
    }
}

function order() {
    createBucketLocalStorage()

    let id = document.getElementById("id").textContent
    addItemBucketLocalStorage(id)

    let size = JSON.parse(localStorage.getItem(bucketName)).length
    document.getElementById("bucket").innerHTML = "КОРЗИНА (" + size + ")"
}

createBucketLocalStorage = function () {
    if (localStorage.getItem(bucketName) == null) {
        localStorage.setItem(bucketName, JSON.stringify([]))
    }
}

addItemBucketLocalStorage = function (elementId) {
    if (localStorage.getItem(bucketName) == null) {
        createBucketLocalStorage()
    }

    let array = JSON.parse(localStorage.getItem(bucketName))
    array.push(elementId)

    console.log(array)
    localStorage.setItem(bucketName, JSON.stringify(array))
}

document.addEventListener("DOMContentLoaded", function() {
    if (localStorage.getItem(bucketName) != null) {
        let size = JSON.parse(localStorage.getItem(bucketName)).length
        document.getElementById("bucket").innerHTML = "КОРЗИНА (" + size + ")"
    }

    document.getElementById("catalog_li").classList.add("active");

    loadProducts()
});