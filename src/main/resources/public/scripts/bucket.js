let bucketName = "bucket"
let emptyMessage =
    "            <div style=\"max-width: 540px;text-align: center;\">\n" +
    "                В корзине нет продуктов :(" +
    "            </div>"
let cost = 0
let myModal
let modelMessageInDocument

async function loadAllBucket() {
    let getByIdUrl = window.location.origin + "/products/"
    let allProductsId = JSON.parse(localStorage.getItem(bucketName))

    let uniqueProductsId = unique(allProductsId)
    if (uniqueProductsId.length === 0) {
        document.getElementById("products").innerHTML = emptyMessage
    }

    cost = 0
    for (let i = 0; i < uniqueProductsId.length; i++) {
        let response = await fetch(getByIdUrl + uniqueProductsId[i])

        if (response.ok) {
            let arrayResponse = await response.json()
            let urlImage = window.location.origin + "/file/" + arrayResponse.file.id
            let numberProducts = numberProduct(allProductsId, uniqueProductsId[i])

            document.getElementById("products").innerHTML +=
                " <div class='productId' hidden>"+ uniqueProductsId[i] +"</div>" +
                "           <div class=\"card mb-3\" style=\"max-width: 540px; border: none;\">\n" +
                "                <div class=\"row g-0\">\n" +
                "                    <div class=\"col-md-2\">\n" +
                "                        <img src=\"" + urlImage + "\" class=\"img-fluid rounded-start bucket-product-img\" alt=\"...\">\n" +
                "                    </div>\n" +
                "                    <div class=\"col-md-10\">\n" +
                "                        <div class=\"card-body\">\n" +
                "                            <h5 class=\"card-title\">" + arrayResponse.name + "</h5>\n" +
                "                            <div class=\"d-grid gap-2 d-md-flex justify-content-md-end\">\n" +
                "                                <button class=\"arrows-button\" onclick=\"removeProduct('" + uniqueProductsId[i] + "')\">\n" +
                "                                    <img src=\"../images/arrows_circle_minus.svg\" class=\"arrows-circle\">\n" +
                "                                </button>\n" +
                "                                <p class=\"p-arrows\" id='numberProducts" + uniqueProductsId[i] + "'>" + numberProducts + "</p>\n" +
                "                                <button class=\"arrows-button\" onclick=\"addProduct('" + uniqueProductsId[i] + "')\">\n" +
                "                                    <img src=\"../images/arrows_circle_plus.svg\" class=\"arrows-circle\">\n" +
                "                                </button>\n" +
                "                                <p class=\"p-arrows\" style=\"margin-left: 40px; margin-right: 40px;\" id='p-cost" + uniqueProductsId[i] + "'>" + arrayResponse.cost * numberProducts + " Р</p>\n" +
                "                                <button class=\"arrows-button\" onclick=\"deleteProduct('" + uniqueProductsId[i] + "')\">\n" +
                "                                    <img src=\"../images/arrows_circle_remove.svg\" class=\"arrows-circle\">\n" +
                "                                </button>\n" +
                "                            </div>" +
                "                        </div>\n" +
                "                    </div>\n" +
                "                </div>\n" +
                "            </div>"

            cost += arrayResponse.cost * numberProducts
        } else {
            alert("Не удалось загрузить корзину продуктов!");
        }
    }
    loadCost(cost)
}

async function loadMainForm() {
    let url = window.location.origin + "/city"

    let cityResponse = await fetch(url)
    if (cityResponse.ok) {
        let cityArray = await cityResponse.json()

        for (let i = 0; i < cityArray.length; i++) {
            document.getElementById("city").innerHTML +=
                "<option value='"+ cityArray[i].shortDescription +"'>"+ cityArray[i].shortDescription +"</option>"
        }
    } else {
        alert("Не удалось загрузить главную форму!");
    }
}

function loadCost(cost) {
    document.getElementById("cost").innerHTML =
        "<strong>Сумма: " + cost + "</strong>"
}

function addProduct(productId) {
    let pCost = Number(document.getElementById("p-cost" + productId).textContent.split(" ", 1))
    let array = JSON.parse(localStorage.getItem(bucketName))
    let oneCost = pCost / numberProduct(array, productId)
    array.push(productId)

    localStorage.setItem(bucketName, JSON.stringify(array))
    let numberProductElement = document.getElementById("numberProducts" + productId)
    let number = Number(numberProductElement.textContent)
    numberProductElement.innerHTML = ++number

    updateBucketNumbers()

    cost += oneCost
    document.getElementById("p-cost" + productId).innerHTML = pCost + oneCost
    loadCost(cost)
}

function removeProduct(productId) {
    let pCost = Number(document.getElementById("p-cost" + productId).textContent.split(" ", 1))
    let array = JSON.parse(localStorage.getItem(bucketName))
    let oneCost = pCost / numberProduct(array, productId)
    array.splice(findFirst(array, productId), 1)

    localStorage.setItem(bucketName, JSON.stringify(array))
    let numberProductElement = document.getElementById("numberProducts" + productId)
    let number = Number(numberProductElement.textContent)
    if (number - 1 === 0) {
        document.getElementById("products").innerHTML = ""

        loadAllBucket()
        updateBucketNumbers()
        return
    }

    numberProductElement.innerHTML = --number

    updateBucketNumbers()

    cost -= oneCost
    document.getElementById("p-cost" + productId).innerHTML = pCost - oneCost
    loadCost(cost)
}

function deleteProduct(productId) {
    let array = JSON.parse(localStorage.getItem(bucketName))
    array = array.filter(el => el !== productId)

    localStorage.setItem(bucketName, JSON.stringify(array))
    document.getElementById("products").innerHTML = ""

    loadAllBucket()
    updateBucketNumbers()
}

async function create() {
    let url = window.location.origin + "/userData"

    let FIO = document.getElementById("FIO").value;
    let email = document.getElementById("email").value;
    let phone = document.getElementById("phone").value;
    let city = document.getElementById("city").value;
    let street = document.getElementById("street").value;
    let house = document.getElementById("house").value;
    let entrance = document.getElementById("entrance").value;
    let floor = document.getElementById("floor").value;
    let apartmentOffice = document.getElementById("apartment_office").value;
    let intercom = document.getElementById("intercom").value;
    let comment = document.getElementById("comment").value;
    let date = document.getElementById("date").value;

    let products = document.getElementsByClassName("productId")
    let arrayProductsIdAndCount = []
    for (let i = 0; i < products.length; i++) {
        let productId = products[i].textContent
        let count = document.getElementById("numberProducts" + productId).textContent

        arrayProductsIdAndCount.push({"id": productId, "numberProduct": count})
    }

    FIO = FIO.split(" ", 3)

    if (arrayProductsIdAndCount.length === 0) {
        modelMessageInDocument.innerHTML = "Необходимо добавить хотя бы один продукт!"
        myModal.show()
        return
    }

    let response = await fetch(url, {
        method: 'POST',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({
            "lastname": FIO[0],
            "name": FIO[1],
            "patronymic": FIO[2],
            "email": email,
            "phone": phone,
            "city": {"shortDescription": city},
            "deliveryType": {"id": "1"},
            "street": street,
            "house": house,
            "entrance": entrance,
            "floor": floor,
            "apartmentOffice": apartmentOffice,
            "intercom": intercom,
            "comment": comment,
            "deliveryInterval": {"id": "1"},
            "deliveryDate": date,
            "productDetails": arrayProductsIdAndCount
        })
    })

    if (response.ok) {
        localStorage.setItem(bucketName, JSON.stringify([]))
        updateBucketNumbers()

        modelMessageInDocument.innerHTML = "Ваш заказ принят в обработку!\nОжидайте звонка от менеджера."
        myModal.show()
    } else {
        modelMessageInDocument.innerHTML = "Мы не смогли обработать запрос :(\nОбратитесь к администратору за помощью."
        myModal.show()
    }
}

unique = function(arr) {
    let result = [];

    for (let str of arr) {
        if (!result.includes(str)) {
            result.push(str);
        }
    }

    return result;
}

findFirst = function (arr, element) {
    for (let i = 0; i < arr.length; i++) {
        if (arr[i] === element) {
            return i
        }
    }
    return -1
}

numberProduct = function (arr, productId) {
    let number = 0;
    for (let i = 0; i < arr.length; i++) {
        if (arr[i] === productId) {
            number++
        }
    }

    return number;
}

updateBucketNumbers = function () {
    if (localStorage.getItem(bucketName) != null) {
        let size = JSON.parse(localStorage.getItem(bucketName)).length
        document.getElementById("bucket").innerHTML = "КОРЗИНА (" + size + ")"
    }
}

document.addEventListener("DOMContentLoaded", async function () {
    myModal = new bootstrap.Modal(document.getElementById('exampleModal'), {})
    modelMessageInDocument = document.getElementById("model-message")

    updateBucketNumbers()

    document.getElementById("bucket_li").classList.add("active");

    await loadAllBucket();
    await loadMainForm()
});