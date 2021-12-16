var spinner = document.getElementById("spinner");
var fatherID;
$(document).ready(handleGetClassificationHierarchy);

/*----------------------------------------------处理结果函数----------------------------------------------------*/
function processGetClassificationsHierarychyResponse(result) {
    console.log("result:" + result);
    var js = JSON.parse(result);

    var map = js["map"];
    var status = js["statusCode"];
    var error = js["error"];
    if (status == 200) {
        var box = document.getElementById("box");
        box.setAttribute("class", "list-group list-group-root well col-md-8 offset-md-2");
        box.innerHTML = "";
        for (var key in map) {
            generateList(map[key], box);
        }

        $('.list-group-item').off("click").on('click', function () {
            $('.caret', this)
                .toggleClass('bi-caret-down-fill')
                .toggleClass('bi-caret-right-fill');
        });
        document.oncontextmenu = null;
        //clear board
        document.addEventListener("contextmenu", function (e) {
            e.preventDefault();
        })
        $(".implementation").off("clcik").on("click", function (e) {
            fatherID = $(e.target).data("identity");
            window.location.href = "ImplePage.html?id=" + fatherID;
        })
        // spinner off
        spinner.style.visibility = "hidden";
    } else {
        alert(error);
        spinner.style.visibility = "hidden";
    }
}


/*-------------------------------------------------工具--------------------------------------------*/
//生成classification hierarychy
function generateList(elem, elemToAppend) {
    if (Object.keys(elem.childern).length === 0) {
        let a = document.createElement("a");
        a.setAttribute("class", "list-group-item classification");
        a.setAttribute("data-toggle", "collapse");
        a.style.paddingLeft = elem.level * 30 + "px";
        a.setAttribute("data-identity", elem.id + "");
        a.setAttribute("data-level", elem.level + "");

        if (!(elem.algos.length == 0)) {
            let div = document.createElement("div");
            div.setAttribute("class", "list-group collapse");
            div.setAttribute("id", elem.name);

            a.setAttribute("href", "#" + elem.name);

            let icon = document.createElement("i");
            icon.setAttribute("class", "bi bi-caret-right-fill caret");
            icon.setAttribute("data-identity", elem.id + "");
            icon.setAttribute("data-level", elem.level + "");

            let span = document.createElement("span");
            span.innerHTML = elem.name;
            span.setAttribute("data-identity", elem.id + "");
            span.setAttribute("data-level", elem.level + "");

            a.appendChild(span);
            a.appendChild(icon);

            for (let j = 0; j < elem.algos.length; j++) {
                let a = document.createElement("a");
                a.setAttribute("href", "#" + elem.algos[j].name);
                a.setAttribute("class", "list-group-item algorithm");
                a.setAttribute("data-toggle", "collapse");
                a.style.paddingLeft = (elem.level + 1) * 30 + "px";
                a.setAttribute("data-identity", elem.algos[j].id + "");
                a.setAttribute("data-level", elem.level + 1 + "");

                let span = document.createElement("span");
                span.innerHTML = elem.algos[j].name;
                span.setAttribute("class", "text-warning bg-opacity-75");
                span.setAttribute("data-identity", elem.algos[j].id + "");
                span.setAttribute("data-level", elem.level + 1 + "");
                a.appendChild(span);
                if (elem.algos[j].imples.length == 0) {
                    div.appendChild(a);
                } else {
                    let icon = document.createElement("i");
                    icon.setAttribute("class", "bi bi-book");
                    icon.setAttribute("data-identity", elem.algos[j].id + "");
                    icon.setAttribute("data-level", elem.level + 1 + "");
                    a.append(icon);

                    let div1 = document.createElement("div");
                    div1.setAttribute("class", "list-group collapse");
                    div1.setAttribute("id", elem.algos[j].name);

                    for (let k = 0; k < elem.algos[j].imples.length; k++) {
                        let a = document.createElement("a");
                        a.setAttribute("href", "#");
                        a.setAttribute("class", "list-group-item implementation text-success bg-opacity-75");
                        a.innerHTML = elem.algos[j].imples[k].language + " implementation";
                        a.style.paddingLeft = (elem.level + 2) * 30 + "px";
                        a.setAttribute("data-identity", elem.algos[j].imples[k].id);
                        div1.appendChild(a);
                    }
                    div.appendChild(a);
                    div.appendChild(div1);
                }

            }
            elemToAppend.appendChild(a);
            elemToAppend.appendChild(div);
        } else {
            a.setAttribute("href", "#");
            a.innerHTML = elem.name;
            elemToAppend.appendChild(a);
        }
    } else {
        let div = document.createElement("div");
        div.setAttribute("class", "list-group collapse");
        div.setAttribute("id", elem.name);

        let a = document.createElement("a");
        a.setAttribute("href", "#" + elem.name);
        a.setAttribute("class", "list-group-item classification");
        a.setAttribute("data-toggle", "collapse");

        let icon = document.createElement("i");
        icon.setAttribute("class", "bi bi-caret-right-fill caret");
        icon.setAttribute("data-identity", elem.id + "");
        icon.setAttribute("data-level", elem.level + "");

        let span = document.createElement("span");
        span.innerHTML = elem.name;
        span.setAttribute("data-identity", elem.id + "");
        span.setAttribute("data-level", elem.level + "");
        a.style.paddingLeft = elem.level * 30 + "px";
        a.setAttribute("data-identity", elem.id + "");
        a.setAttribute("data-level", elem.level + "");
        for (let j = 0; j < elem.childrenID.length; j++) {
            generateList(elem.childern[elem.childrenID[j]], div);
        }
        //添加算法目录
        if (!(elem.algos.length == 0)) {
            for (let j = 0; j < elem.algos.length; j++) {
                let a = document.createElement("a");
                a.setAttribute("href", "#" + elem.algos[j].name);
                a.setAttribute("class", "list-group-item algorithm");
                a.setAttribute("data-toggle", "collapse");
                a.style.paddingLeft = (elem.level + 1) * 30 + "px";
                a.setAttribute("data-identity", elem.algos[j].id + "");
                a.setAttribute("data-level", elem.level + 1 + "");

                let span = document.createElement("span");
                span.innerHTML = elem.algos[j].name;
                span.setAttribute("class", "text-warning bg-opacity-75");
                span.setAttribute("data-identity", elem.algos[j].id + "");
                span.setAttribute("data-level", elem.level + 1 + "");
                a.appendChild(span)
                if (elem.algos[j].imples.length == 0) {
                    div.appendChild(a);
                } else {
                    let icon = document.createElement("i");
                    icon.setAttribute("class", "bi bi-book");
                    icon.setAttribute("data-identity", elem.algos[j].id + "");
                    icon.setAttribute("data-level", elem.level + 1 + "");
                    a.append(icon);
                    let div1 = document.createElement("div");
                    div1.setAttribute("class", "list-group collapse");
                    div1.setAttribute("id", elem.algos[j].name);

                    for (let k = 0; k < elem.algos[j].imples.length; k++) {
                        let a = document.createElement("a");
                        a.setAttribute("href", "#");
                        a.setAttribute("class", "list-group-item implementation text-success bg-opacity-75");
                        a.innerHTML = elem.algos[j].imples[k].language + " implementation";
                        a.style.paddingLeft = (elem.level + 2) * 30 + "px";
                        a.setAttribute("data-identity", elem.algos[j].imples[k].id);
                        div1.appendChild(a);
                    }
                    div.appendChild(a);
                    div.appendChild(div1);
                }

            }
        }
        a.appendChild(span);
        a.appendChild(icon);
        elemToAppend.appendChild(a);
        elemToAppend.appendChild(div);
    }
}

/*-------------------------------------------------处理函数------------------------------------------------*/
//显示请求处理
function handleGetClassificationHierarchy() {
    spinner.style.visibility = "visible";
    var xhr = new XMLHttpRequest();
    console.log(xhr);

    xhr.open("GET", getClassificationsHierarychy_url, true);
    // send the collected data as JSON
    xhr.send();
    // This will process results and update HTML as appropriate. 
    xhr.onloadend = function () {
        console.log(xhr);
        console.log(xhr.request);

        if (xhr.readyState == XMLHttpRequest.DONE) {
            console.log("XHR:" + xhr.responseText);
            processGetClassificationsHierarychyResponse(xhr.responseText);
        } else {
            processGetClassificationsHierarychyResponse("N/A");
        }
    };
}