var registButton = document.getElementById("registButton");
var spinner = document.getElementById("spinner");


registButton.onclick = handClick;
var arg1;
var arg2;

String.prototype.hashCode = function () {
    var hash = 0, i, chr;
    if (this.length === 0) return hash;
    for (i = 0; i < this.length; i++) {
        chr = this.charCodeAt(i);
        hash = ((hash << 5) - hash) + chr;
        hash |= 0; // Convert to 32bit integer
    }
    return hash;
};

function processRegistResponse(result) {
    // Can grab any DIV or SPAN HTML element and can then manipulate its
    // contents dynamically via javascript
    console.log("result:" + result);
    var js = JSON.parse(result);

    var response = js["response"];
    var httpCode = js["httpCode"];

    if (httpCode == 200) {
        // Update computation result
        localStorage.setItem("username", arg1);
        localStorage.setItem("password", arg2.hashCode());
        window.location.href = "registerPage.html";
    } else {
        alert(response);
    }
}

function handClick() {
    spinner.style.display = "block";
    var form = document.regist;
    arg1 = form.arg1.value;
    arg2 = form.arg2.value;
    var arg3 = form.arg3.value;
    if (arg2 != arg3) {
        return;
    }

    var data = {};
    data["username"] = arg1;
    data["password"] = arg2.hashCode();

    var js = JSON.stringify(data);
    console.log("JS:" + js);
    var xhr = new XMLHttpRequest();
    console.log(xhr);
    xhr.open("POST", regist_url, true);
    // send the collected data as JSON
    xhr.send(js);
    // This will process results and update HTML as appropriate. 
    xhr.onloadend = function () {
        console.log(xhr);
        console.log(xhr.request);

        if (xhr.readyState == XMLHttpRequest.DONE) {
            console.log("XHR:" + xhr.responseText);
            processRegistResponse(xhr.responseText);
        } else {
            processRegistResponse("N/A");
        }
    };

}