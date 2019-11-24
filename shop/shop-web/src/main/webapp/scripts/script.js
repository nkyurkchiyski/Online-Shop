function getProductIds() {
    const ids = [];
    let elems = $(".productId");
    for (let i = 0; i < elems.length; i++) {
        ids.push(elems[i].innerText);
    }
    return ids;
}

function getProductOrders(ids) {
    const products = [];
    for (let i = 0; i < ids.length; i++) {
        let quantity = $("#product_" + ids[i] + "_quantity").val();
        products.push({
            productId: ids[i],
            quantity: quantity
        });
    }
    return products;
}

function getProductId(id) {
    return id.replace('product_', '');
}

function updateCart() {
    let productElements = $(".product");
    let total = 0;
    for (let i = 0; i < productElements.length; i++) {
        let elemId = productElements[i].id;
        let prPrice = $("#" + elemId + "_price").text().replace('$', '');
        let prQuantity = $("#" + elemId + "_quantity").val();
        let prTotal = (prPrice * prQuantity).toFixed(2);
        $("#" + elemId + "_total").text("$" + prTotal);
        total += Number(prTotal);
    }
    $("#grandTotal").text("$" + total.toFixed(2));
}

function removeProduct(element) {
    element.remove()
    updateCart();
}

function update() {
    let elements = $('.quantity');
    for (let i = 0; i < elements.length; i++) {
        elements[i].addEventListener('change', updateCart, false);
    }

}

function redirectToHome() {
    window.location.href = "http://localhost:8181/online-shop/home";
}

function sendRequest(type, url, success, failure, data) {
    $.ajax({
        type: type,
        url: url,
        data: data,
        contentType: "application/json; charset=utf-8",
        dataType: "json",
        success: success,
        failure: failure
    });
}