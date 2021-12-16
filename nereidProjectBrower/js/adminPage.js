var spinner = document.getElementById("spinner");
var mask = document.getElementsByClassName("mask")[0];
var fatherID;
var locatedLevel;
var addClassificationTarget;
$(document).ready(functionAuthentication);
$(document).ready(handleGetClassificationHierarchy);

/*----------------------------------------------处理结果函数----------------------------------------------------*/
function processGetClassificationsHierarychyResponse(result) {
    console.log("result:" + result);
    var js = JSON.parse(result);

    var map = js["map"];
    console.log("map")
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

        //添加点击事件
        var menue1 = document.getElementById("menue1");
        var menue2 = document.getElementById("menue2");
        $(".classification").off("contextmenu").on("contextmenu", function (e) {
            fatherID = $(e.target).data("identity");
            locatedLevel = $(e.target).data("level");
            if (e.button == 2) {
                menue2.style.display = 'none';
                menue1.style.display = 'block';
                menue1.style.top = (e.clientY + 10) + 'px';
                menue1.style.left = (e.clientX + 10) + 'px';
            }
            e.preventDefault();
            e.stopPropagation();
        })
        $(".algorithm").off("contextmenu").on("contextmenu", function (e) {
            fatherID = $(e.target).data("identity");
            locatedLevel = $(e.target).data("level");
            if (e.button == 2) {
                menue1.style.display = 'none';
                menue2.style.display = 'block';
                menue2.style.top = (e.clientY + 10) + 'px';
                menue2.style.left = (e.clientX + 10) + 'px';
            }
            e.preventDefault();
            e.stopPropagation();
        })

        $(".implementation").off("clcik").on("click", function (e) {
            fatherID = $(e.target).data("identity");
            window.location.href = "impleAdminPage.html?id=" + fatherID;
        })
        document.onclick = null;
        document.oncontextmenu = null;
        //clear board
        document.addEventListener("click", function () {
            menue2.style.display = 'none';
            menue1.style.display = 'none';
        })
        document.addEventListener("contextmenu", function (e) {
            menue2.style.display = 'none';
            menue1.style.display = 'none';
            e.preventDefault();
        })

        $('#removeAlgorithmButton').on('click', function (e) {
            deletAlgorithm(fatherID);
        });

        $("#removeClassificationButton").off("click").on("click", function (e) {
            deleteClassfication(fatherID);
        })

        $("#getRegisteredUserButton").off("click").on("click", function (e) {
            mask.style.display = "block";
            handlerGetUsers();
        })

        $(".closeButton").off("click").on("click", function (e) {
            mask.style.display = "none";
            $(".userListBoard").css("display", "none");
            $(".userListBoard").css({ "display": "none", "z-index": "3" });
        })

        $(".closeButton1").off("click").on("click", function (e) {
            $(".userActivitesBoard").css("display", "none");
            $(".userActivitesBoard").css({ "display": "none", "z-index": "3" });
        })

        // spinner off
        spinner.style.visibility = "hidden";
        mask.style.display = "none";
        $("#dropdownAction").css("visibility", "visible")

    } else {
        alert(error);
        spinner.style.visibility = "hidden";
        mask.style.display = "none";
        $("#dropdownAction").css("visibility", "visible");
    }
}

function handlerGetUsers() {
    spinner.style.visibility = "visible";
    var xhr = new XMLHttpRequest();

    xhr.open("GET", getRegisteredUsers_url, true);
    // send the collected data as JSON
    xhr.send();
    // This will process results and update HTML as appropriate. 
    xhr.onloadend = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            console.log("XHR:" + xhr.responseText);
            processGetRegisteredUsersResponse(xhr.responseText);
        } else {
            processGetRegisteredUsersResponse("N/A");
        }
    };
}

function processGetRegisteredUsersResponse(result) {
    spinner.style.visibility = "hidden";
    var js = JSON.parse(result);
    var httpCode = js["httpCode"];
    var users = js["users"];
    if (httpCode == 200) {
        let tbody = document.getElementsByClassName("boxsss")[0];
        tbody.innerHTML = "";
        for (let i = 0; i < users.length; i++) {
            let tr = document.createElement("tr");
            let th = document.createElement("th");
            th.setAttribute("scope", "row");
            th.innerHTML = i;
            let td1 = document.createElement("td");
            td1.innerHTML = users[i].username;
            td1.setAttribute("class", "getActivityTd");
            td1.setAttribute("data-usernamex", users[i].username);
            let td2 = document.createElement("td");
            td2.innerHTML = users[i].role;
            let td3 = document.createElement("td");
            let icon = document.createElement("i");
            icon.setAttribute("class", "bi bi-x-lg");
            icon.setAttribute("data-username", users[i].username);
            td3.appendChild(icon);
            tr.appendChild(th);
            tr.appendChild(td1);
            tr.appendChild(td2);
            tr.appendChild(td3);
            tbody.appendChild(tr);
        }
        $(".bi-x-lg").off("click").on("click", function (e) {
            handleDeleteUser($(this).data("username"));
        })
        $(".getActivityTd").off("click").on("click", function (e) {
            $(".userActivitesBoard").css({ "display": "block", "z-index": "10" });
            $(".mask1").css("display", "block");
            handleGetActivities($(this).data("usernamex"));
        })
        $(".userListBoard").css({ "display": "block", "z-index": "6" });

    } else {
        alert("Fail to get registered users");
        $(".userListBoard").css({ "display": "block", "z-index": "6" });
    }
}

function handleGetActivities(name) {
    spinner.style.visibility = "visible";

    var data = {};
    data["name"] = name;
    var js = JSON.stringify(data);

    var xhr = new XMLHttpRequest();
    xhr.open("POST", getActivities_url, true);
    xhr.send(js);

    xhr.onloadend = function () {
        // console.log(xhr);
        // console.log(xhr.request);

        if (xhr.readyState == XMLHttpRequest.DONE) {
            processGetActivitiesResponse(xhr.responseText);
        } else {
            processGetActivitiesResponse("N/A");
        }
    };
}

function processGetActivitiesResponse(result) {
    $(".mask1").css("display", "none");
    spinner.style.visibility = "hidden";
    var js = JSON.parse(result);
    var httpCode = js["httpCode"];
    var activities = js["activities"];
    if (httpCode = 200) {
        console.log(activities);
        let tbody = document.getElementsByClassName("boxggg")[0];
        tbody.innerHTML = "";
        for (let i = 0; i < activities.length; i++) {
            let tr = document.createElement("tr");
            let th1 = document.createElement("th");
            th1.setAttribute("scope", "row");
            th1.innerHTML = i;
            let th2 = document.createElement("th");
            th2.innerHTML = activities[i].name;
            let th3 = document.createElement("th");
            th3.innerHTML = activities[i].activity;
            let th4 = document.createElement("th");
            th4.innerHTML = activities[i].time;
            tr.appendChild(th1);
            tr.appendChild(th2);
            tr.appendChild(th3);
            tr.appendChild(th4);
            tbody.appendChild(tr);
        }
    } else {
        alert("Fail to get user's activity");
    }
}

function handleDeleteUser(name) {
    spinner.style.visibility = "visible";
    $(".userListBoard").css({ "display": "block", "z-index": "3" });
    mask.style.display = "block";
    var xhr = new XMLHttpRequest();
    xhr.open("POST", deleteRegisteredUser_url + "/" + name, true);
    xhr.send(null);

    xhr.onloadend = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            // console.log("XHR:" + xhr.responseText);
            processDeleteRegisteredUser(xhr.responseText);
        } else {
            processDeleteRegisteredUser("N/A");
        }
    };
}
function processDeleteRegisteredUser(result) {
    var js = JSON.parse(result);
    var httpCode = js["httpCode"];
    if (httpCode == 200) {
        handlerGetUsers();
    } else {
        spinner.style.visibility = "hidden";
        mask.style.display = "none";
        alert("Fail to delete algorithm");
    }
}
function deleteClassfication(id) {
    spinner.style.visibility = "visible";
    mask.style.display = "block";
    var xhr = new XMLHttpRequest();
    xhr.open("POST", deleteClassification_url + "/" + id, true);
    xhr.send(null);

    xhr.onloadend = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            // console.log("XHR:" + xhr.responseText);
            processDeleteclassification(xhr.responseText);
        } else {
            processDeleteclassification("N/A");
        }
    };
}
function processDeleteclassification(result) {
    var js = JSON.parse(result);
    var httpCode = js["httpCode"];
    if (httpCode == 200) {
        handleGetClassificationHierarchy();
    } else {
        spinner.style.visibility = "hidden";
        mask.style.display = "none";
        alert("Fail to delete algorithm");
    }
}
function deletAlgorithm(id) {
    spinner.style.visibility = "visible";
    mask.style.display = "block"
    var xhr = new XMLHttpRequest();
    xhr.open("POST", deleteAlgorithm_url + "/" + id, true);
    xhr.send(null);

    xhr.onloadend = function () {
        if (xhr.readyState == XMLHttpRequest.DONE) {
            // console.log("XHR:" + xhr.responseText);
            processDeleteAlgorithm(xhr.responseText);
        } else {
            processDeleteAlgorithm("N/A");
        }
    };
}

function processDeleteAlgorithm(result) {
    var js = JSON.parse(result);
    var httpCode = js["httpCode"];
    if (httpCode == 200) {
        handleGetClassificationHierarchy();
    } else {
        spinner.style.visibility = "hidden";
        mask.style.display = "none";
        alert("Fail to delete algorithm");
    }
}
function processAuthenticationResponse(result) {
    console.log("result:" + result);
    var js = JSON.parse(result);

    var status = js["statusCode"];
    if (status == 200) {
        console.log("认证成功");
    } else {
        window.location.href = "loginPage.html"
        console.log("认证失败");
    }
}
function functionAuthentication() {
    if (localStorage.hasOwnProperty("username") && localStorage.hasOwnProperty("password")) {
        console.log("我有用户名密码");
        var username = localStorage.getItem("username");
        var password = localStorage.getItem("password");

        var data = {};
        data["arg1"] = username;
        data["arg2"] = password;

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
                processAuthenticationResponse(xhr.responseText);
            } else {
                processAuthenticationResponse("N/A");
            }
        };
    } else {
        window.location.href = "loginPage.html"
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
                        console.log(elem.algos[j].imples[k].language)
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