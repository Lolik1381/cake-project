async function loadProducts() {
    let location = window.location.href

    let split = location.split("/")
    let productId = split[split.length - 1]

    let response = await fetch("/products/" + productId)
    console.log(await response.json())
}


document.addEventListener("DOMContentLoaded", function() {
    loadProducts()
});