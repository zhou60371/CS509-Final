var loginButton = document.getElementById('loginButton');

loginButton.onclick = handleClick;

function processLoginResponse(result) {
    // Can grab any DIV or SPAN HTML element and can then manipulate its
    // contents dynamically via javascript
    console.log("result:" + result);
    var js = JSON.parse(result);

    var result = js["result"];
    var status = js["statusCode"];
    var error = js["error"];
    var role = js["role"];

    if (status == 200) {
        if (role == 1) {
            window.location.href = "../registerPage.html";
        } else if (role == 0) {
            alert(123)
            window.location.href = "C:/Users/ZZH/Desktop/HW/software designning/nereidProject/adminPage.html";
        }
    } else {
        alert(error);
    }
}

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

function handleClick(e) {
    var form = document.login;
    var arg1 = form.arg1.value;
    var arg2 = form.arg2.value;

    var data = {};
    data["arg1"] = arg1;
    data["arg2"] = arg2.hashCode();

    var js = JSON.stringify(data);
    console.log("JS:" + js);
    var xhr = new XMLHttpRequest();
    console.log(xhr);
    xhr.open("POST", login_url, true);
    // send the collected data as JSON
    xhr.send(js);
    // This will process results and update HTML as appropriate. 
    xhr.onloadend = function () {
        console.log(xhr);
        console.log(xhr.request);

        if (xhr.readyState == XMLHttpRequest.DONE) {
            console.log("XHR:" + xhr.responseText);
            processLoginResponse(xhr.responseText);
        } else {
            processLoginResponse("N/A");
        }
    };
}


