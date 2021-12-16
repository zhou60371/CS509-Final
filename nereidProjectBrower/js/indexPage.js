var spinner = document.getElementById("spinner");
var mask = document.getElementsByClassName("mask")[0];
var fatherID;
var locatedLevel;
var addClassificationTarget;
var reclassifying = 0;
var algorithmid = 0;
var classificationid = 0;
var merging = 0;
var stage = 0;
var class1 = 0;
var fatheridid1 = 0;
var class2 = 0;
$(document).ready(functionAuthentication);
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
            window.location.href = "impleRegisterPage.html?id=" + fatherID;
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
        //add top classification
        $("#addTopClassification").off("click").on("click", function (e) {
            $(".addClassificationBoard").css({ "display": "block", "z-index": "3" });
            mask.style.display = "block";
        })

        $(".addClassificationBoard button.addTopConcel").off("click").on("click", function (e) {
            $(".addClassificationBoard").css({ "display": "none", "z-index": "-3" });
            mask.style.display = "none";
        })

        $(".addClassificationBoard button.addTopSubmit").off("click").on("click", function (e) {
            $(".addClassificationBoard").css({ "display": "none", "z-index": "-3" });
            handleAddTopClassificationClick();
            $("#inputNewClassificationName").val("");
            e.stopPropagation();
        })

        //add sub classification
        $(".addSubClassification").off("click").on("click", function (e) {
            $(".addClassificationBoard1").css({ "display": "block", "z-index": "3" });
            mask.style.display = "block";
        })
        $(".addClassificationBoard1 button.addSubConcel").off("click").on("click", function (e) {
            $(".addClassificationBoard1").css({ "display": "none", "z-index": "-3" });
            mask.style.display = "none";
        })
        $(".addClassificationBoard1 button.addSubSubmit").off("click").on("click", function (e) {
            $(".addClassificationBoard1").css({ "display": "none", "z-index": "-3" });
            handleAddSubClassificationClick()
            $("#inputNewSubClassificationName").val("");
            e.stopPropagation();
        })

        //add algorithm
        $("button.addAlgorithmButton").off("click").on("click", function (e) {
            $("div.addAlgorithmBoard").css({ "display": "block", "z-index": "3" });
            mask.style.display = "block";
        })
        $("div.addAlgorithmBoard button.addAlgoConcel").off("click").on("click", function (e) {
            $("div.addAlgorithmBoard").css({ "display": "none", "z-index": "-3" });
            mask.style.display = "none";
        })
        $("div.addAlgorithmBoard button.addAlgoSubmit").off("click").on("click", function (e) {
            $("div.addAlgorithmBoard").css({ "display": "none", "z-index": "-3" });
            handleAddAlgorithmClick();
            $("input#inputNewAlgorithmName").val("");
            $("#inputNewAlgorithmDescription").val("");
            e.stopPropagation();
        })

        //add implementation
        $("button.uploadImplementationButton").off("click").on("click", function (e) {
            $("div.uploadImplementationBoard").css({ "display": "block", "z-index": "3" });
            mask.style.display = "block";
        })
        $("div.uploadImplementationBoard button.addImplementationConcel").off("click").on("click", function (e) {
            $("div.uploadImplementationBoard").css({ "display": "none", "z-index": "-3" });
            mask.style.display = "none";
        })
        $("div.uploadImplementationBoard button.addImplementationSubmit").off("click").on("click", function (e) {
            $("div.uploadImplementationBoard").css({ "display": "none", "z-index": "-3" });
            handUploadImplementation();
            $("input#inputNewImplementationLanguage").val("");
            $("textarea#inputNewImplementationContent").val("");
        })

        //reclassify algorithm
        $("#reclassifyAlgorithm").off("click").on("click", function (e) {
            $(".algoGroup2").css("display", "block");
            $("#dropdownAction button").attr("disabled", true);
            reclassifying = 1;
            mask.style.display = "block";
            $(".confirmReclassifyAlgoBoard").css({ "display": "block", "z-index": "3" });
            $("#reclassifyButton1").attr("disabled", true);
            $("#reclassifyButton2").attr("disabled", true);
        })

        $("#confirmReclassifyAlgoBoardButton").off("click").on("click", function (e) {
            $(".confirmReclassifyAlgoBoard").css({ "display": "none", "z-index": "-3" });
            $(".algorithmBox").css("display", "block");
            mask.style.display = "none";
        })

        $("#reclassifyButton2").off("click").on("click", function (e) {
            mask.style.display = "block";
            $(".algoGroup2").css("display", "none");
            $(".algoGroup1").css("display", "block");
            $(".algorithmBox").css("display", "none");
            $(".checkBox").removeClass("bg-warning");
            $(".confirmReclassifyAlgoBoard2").css({ "display": "block", "z-index": "3" });
        })

        $("#confirmReclassifyAlgoBoardButton2").off("click").on("click", function (e) {
            $(".confirmReclassifyAlgoBoard2").css({ "display": "none", "z-index": "-3" });
            $(".classificationBox").css("display", "block");
            mask.style.display = "none";
        })

        $("button#reclassifyCloseButton2").off("click").on("click", function (e) {
            $("#dropdownAction button").attr("disabled", false);
            $(".algoGroup2").css("display", "none");
            $(".checkBox").css("display", "none");
            reclassifying = 0;
            $(".checkBox").removeClass("bg-warning");
            algorithmid = 0;
            classificationid = 0;
            $("#reclassifyButton1").attr("disabled", true);
            $("#reclassifyButton2").attr("disabled", true);
        })

        $("#reclassifyButton1").off("click").on("click", function (e) {
            mask.style.display = "block";
            $(".algoGroup1").css("display", "none");
            $(".classificationBox").css("display", "none");
            $(".checkBox").removeClass("bg-warning");
            handleReclassifyAlgorithm();
        })

        $("button#reclassifyCloseButton1").off("click").on("click", function (e) {
            $("#dropdownAction button").attr("disabled", false);
            $(".algoGroup1").css("display", "none");
            $(".checkBox").css("display", "none");
            reclassifying = 0;
            $(".checkBox").removeClass("bg-warning");
            algorithmid = 0;
            classificationid = 0;
            $("#reclassifyButton1").attr("disabled", true);
            $("#reclassifyButton2").attr("disabled", true);
        })

        $(".algorithmBox").off("click").on("click", function (e) {
            e.stopPropagation();
            if (reclassifying == 1) {
                if (algorithmid == 0) {
                    $(this).addClass("bg-warning");
                    algorithmid = $(this).data("algorithmid");
                } else {
                    if (algorithmid == $(this).data("algorithmid")) {
                        algorithmid = 0;
                        $(this).removeClass("bg-warning");
                    }
                }
                if (algorithmid == 0) {
                    $("#reclassifyButton2").attr("disabled", true);
                } else {
                    $("#reclassifyButton2").attr("disabled", false);
                }
            }
        })

        $(".classificationBox").off("click").on("click", function (e) {
            e.stopPropagation();
            if (reclassifying == 1) {
                if (classificationid == 0) {
                    $(this).addClass("bg-warning");
                    classificationid = $(this).data("classificationid");
                } else {
                    if (classificationid == $(this).data("classificationid")) {
                        classificationid = 0;
                        $(this).removeClass("bg-warning");
                    }
                }
                if (classificationid == 0) {
                    $("#reclassifyButton1").attr("disabled", true);
                } else {
                    $("#reclassifyButton1").attr("disabled", false);
                }
            }
            if (merging == 1) {
                let choosed = $(this).hasClass("choosedClass1");
                if (stage == 1) {
                    if (class1 == 0) {
                        $(this).addClass("bg-warning");
                        $(this).addClass("choosedClass1");
                        class1 = $(this).data("classificationid");
                        fatheridid1 = $(this).data("fatheridid");
                    } else {
                        if (class1 == $(this).data("classificationid")) {
                            class1 = 0;
                            fatheridid1 = 0;
                            $(this).removeClass("bg-warning");
                            $(this).removeClass("choosedClass1");
                        }
                    }
                } else {
                    if (!choosed) {
                        if (class2 == 0 && $(this).data("fatheridid") == fatheridid1) {
                            $(this).addClass("bg-warning");
                            class2 = $(this).data("classificationid");
                        } else {
                            if (class2 == $(this).data("classificationid")) {
                                class2 = 0;
                                $(this).removeClass("bg-warning");
                            }
                        }
                    }
                }
                if (class1 == 0) {
                    $("#mergeButton3").attr("disabled", true);
                } else {
                    $("#mergeButton3").attr("disabled", false);
                }
                if (class2 == 0) {
                    $("#mergeButton4").attr("disabled", true);
                } else {
                    $("#mergeButton4").attr("disabled", false);
                }
            }
        })

        //merge sibiling classifications
        $("#mergeSibilingClassification").off("click").on("click", function (e) {
            merging = 1;
            stage = 1;
            $(".algoGroup3").css("display", "block");
            $("#dropdownAction button").attr("disabled", true);
            mask.style.display = "block";
            $(".mergeBoard1").css({ "display": "block", "z-index": "3" });
            $("#mergeButton3").attr("disabled", true);
            $("#mergeButton4").attr("disabled", true);
        })

        $("#mergeBoardButton1").off("click").on("click", function (e) {
            $(".mergeBoard1").css({ "display": "none", "z-index": "-3" });
            $(".classificationBox").css("display", "block");
            mask.style.display = "none";
        })

        $("#mergeButton3").off("click").on("click", function (e) {
            mask.style.display = "block";
            $(".mergeBoard2").css({ "display": "block", "z-index": "3" });
            $(".algoGroup3").css("display", "none");
            $(".algoGroup4").css("display", "block");
            stage = 2;
        })

        $("#mergeBoardButton2").off("click").on("click", function (e) {
            mask.style.display = "none";
            $(".mergeBoard2").css({ "display": "none", "z-index": "-3" });
        })

        $("#mergeButton4").off("click").on("click", function (e) {
            mask.style.display = "block";
            handleMergerSibilingClassification();
        })

        $("#mergeCloseButton3").off("click").on("click", function (e) {
            $("#dropdownAction button").attr("disabled", false);
            $("#mergeButton3").attr("disabled", true);
            $("#mergeButton4").attr("disabled", true);
            class1 = 0;
            class2 = 0;
            fatheridid1 = 0;
            $(".checkBox").css("display", "none");
            merging = 0;
            $(".checkBox").removeClass("bg-warning");
            $(".algoGroup3").css("display", "none");
        })

        $("#mergeCloseButton4").off("click").on("click", function (e) {
            $("#dropdownAction button").attr("disabled", false);
            $("#mergeButton3").attr("disabled", true);
            $("#mergeButton4").attr("disabled", true);
            class1 = 0;
            class2 = 0;
            fatheridid1 = 0;
            $(".checkBox").css("display", "none");
            merging = 0;
            $(".checkBox").removeClass("bg-warning");
            $(".algoGroup4").css("display", "none");
        })
        // spinner off
        spinner.style.visibility = "hidden";
        mask.style.display = "none";
        $("#dropdownAction").css("visibility", "visible");
    } else {
        alert(error);
        spinner.style.visibility = "hidden";
        mask.style.display = "none";
        $("#dropdownAction").css("visibility", "visible")
    }
}
function handleMergerSibilingClassification() {
    spinner.style.visibility = "visible";
    $(".algoGroup4").css("display", "none");
    let classification2 = class1;
    let classification1 = class2;
    var data = {};
    data["class1"] = classification1;
    data["class2"] = classification2;

    var js = JSON.stringify(data);

    var xhr = new XMLHttpRequest();
    xhr.open("POST", mergeSibilingClassification_url, true);
    // send the collected data as JSON
    xhr.send(js);
    // This will process results and update HTML as appropriate. 
    xhr.onloadend = function () {
        console.log(xhr);
        console.log(xhr.request);

        if (xhr.readyState == XMLHttpRequest.DONE) {
            console.log("XHR:" + xhr.responseText);
            processMergeSibilingClassificationResponse(xhr.responseText);
        } else {
            processMergeSibilingClassificationResponse("N/A");
        }
    };
}
function processMergeSibilingClassificationResponse(result) {
    $("#dropdownAction button").attr("disabled", false);
    class1 = 0;
    class2 = 0;
    fatheridid1 = 0;
    var js = JSON.parse(result);

    var httpCode = js["httpCode"];
    if (httpCode == 200) {
        handleGetClassificationHierarchy();
        handleUploadActivity("Merge sibiling classifications.");
    } else {
        alert("Fail to merge classification");
    }
}
function handleReclassifyAlgorithm() {
    spinner.style.visibility = "visible";
    let algoId = algorithmid;
    let classiId = classificationid;
    var data = {};
    data["algoId"] = algoId;
    data["classiId"] = classiId;

    var js = JSON.stringify(data);

    var xhr = new XMLHttpRequest();
    xhr.open("POST", reclassifyAlgorithm_url, true);
    // send the collected data as JSON
    xhr.send(js);
    // This will process results and update HTML as appropriate. 
    xhr.onloadend = function () {
        console.log(xhr);
        console.log(xhr.request);

        if (xhr.readyState == XMLHttpRequest.DONE) {
            console.log("XHR:" + xhr.responseText);
            processReclassifyAlgorithmResponse(xhr.responseText);
        } else {
            processReclassifyAlgorithmResponse("N/A");
        }
    };
}

function processReclassifyAlgorithmResponse(result) {
    $("#dropdownAction button").attr("disabled", false);
    algorithmid = 0;
    classificationid = 0;
    reclassifying = 0;
    var js = JSON.parse(result);

    var httpCode = js["httpCode"];
    if (httpCode == 200) {
        handleGetClassificationHierarchy();
        handleUploadActivity("Reclassify algorithm");
    } else {
        alert("Fail to reclassify algorithm");
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
//添加类别结果处理
function processAddclassificationResponse(result) {
    spinner.style.visibility = "hidden";
    console.log("result:" + result);
    var js = JSON.parse(result);

    var response = js["response"];
    var httpCode = js["httpCode"];

    if (httpCode == 200) {
        handleGetClassificationHierarchy();
        handleUploadActivity("add classification");
        console.log("sucess");
    } else {
        alert("not success");
        spinner.style.visibility = "hidden";
        mask.style.display = "none";
    }
}

//添加算法结果处理
function processAddAlgorithmResponse(result) {
    spinner.style.visibility = "hidden";
    console.log("result:" + result);
    var js = JSON.parse(result);

    var response = js["response"];
    var httpCode = js["httpCode"];

    if (httpCode == 200) {
        handleGetClassificationHierarchy();
        handleUploadActivity("add algorithm");
    } else {
        alert("not success");
        spinner.style.visibility = "hidden";
        mask.style.display = "none";
    }
}
//添加实现结果处理
function processUploadImplementationResponse(result) {
    spinner.style.visibility = "hidden";
    console.log("result:" + result);
    var js = JSON.parse(result);

    var response = js["response"];
    var httpCode = js["httpCode"];

    if (httpCode == 200) {
        handleGetClassificationHierarchy();
        console.log("upload implementation sucess");
        handleUploadActivity("upload implementation");
    } else {
        alert("upload implementation not success");
        mask.style.display = "none";
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
            let icon2 = document.createElement("i");
            icon2.setAttribute("class", "bi bi-square checkBox classificationBox");
            icon2.setAttribute("data-classificationId", elem.id);
            icon2.setAttribute("data-fatheridid", elem.fatherID);
            icon2.setAttribute("data-level", elem.level + "");

            let span = document.createElement("span");
            span.innerHTML = elem.name;
            span.setAttribute("data-identity", elem.id + "");
            span.setAttribute("data-level", elem.level + "");

            a.appendChild(span);
            a.appendChild(icon);
            a.appendChild(icon2);

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
                    let icon3 = document.createElement("i");
                    icon3.setAttribute("class", "bi bi-square checkBox algorithmBox");
                    icon3.setAttribute("data-algorithmId", elem.algos[j].id);
                    icon3.setAttribute("data-level", elem.level + "");
                    a.appendChild(icon3);
                    div.appendChild(a);
                } else {
                    let icon = document.createElement("i");
                    icon.setAttribute("class", "bi bi-book");
                    icon.setAttribute("data-identity", elem.algos[j].id + "");
                    icon.setAttribute("data-level", elem.level + 1 + "");
                    let icon3 = document.createElement("i");
                    icon3.setAttribute("class", "bi bi-square checkBox algorithmBox");
                    icon3.setAttribute("data-algorithmId", elem.algos[j].id);
                    icon3.setAttribute("data-level", elem.level + "");
                    a.append(icon);
                    a.appendChild(icon3);

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
            let icon2 = document.createElement("i");
            icon2.setAttribute("class", "bi bi-square checkBox classificationBox");
            icon2.setAttribute("data-classificationId", elem.id);
            icon2.setAttribute("data-fatheridid", elem.fatherID);
            icon2.setAttribute("data-level", elem.level + "");
            a.setAttribute("href", "#");
            a.innerHTML = elem.name;
            a.appendChild(icon2);
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
        let icon2 = document.createElement("i");
        icon2.setAttribute("class", "bi bi-square checkBox classificationBox");
        icon2.setAttribute("data-classificationId", elem.id);
        icon2.setAttribute("data-fatheridid", elem.fatherID);
        icon2.setAttribute("data-level", elem.level + "");

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
                a.appendChild(span);

                if (elem.algos[j].imples.length == 0) {
                    let icon3 = document.createElement("i");
                    icon3.setAttribute("class", "bi bi-square checkBox algorithmBox");
                    icon3.setAttribute("data-algorithmId", elem.algos[j].id);
                    icon3.setAttribute("data-level", elem.level + "");
                    a.appendChild(icon3);
                    div.appendChild(a);
                } else {
                    let icon = document.createElement("i");
                    icon.setAttribute("class", "bi bi-book");
                    icon.setAttribute("data-identity", elem.algos[j].id + "");
                    icon.setAttribute("data-level", elem.level + 1 + "");
                    a.append(icon);
                    let icon3 = document.createElement("i");
                    icon3.setAttribute("class", "bi bi-square checkBox algorithmBox");
                    icon3.setAttribute("data-algorithmId", elem.algos[j].id);
                    icon3.setAttribute("data-level", elem.level + "");
                    a.appendChild(icon3);
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
        a.appendChild(icon2);
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
function handleUploadActivity(activity) {
    console.log(11);
    var d = new Date();
    var year = d.getFullYear();
    var month = d.getMonth();
    var day = d.getDate();
    var hours = d.getHours();
    var minites = d.getMinutes();
    var date = "" + year + "-" + month + "-" + day + " " + hours + ":" + minites;
    var name = localStorage.getItem("username");

    var data = {};
    data["name"] = name;
    data["activity"] = activity;
    data["time"] = date;

    var js = JSON.stringify(data);

    var xhr = new XMLHttpRequest();
    xhr.open("POST", uploadActivity_url, true);
    // send the collected data as JSON
    xhr.send(js);
    // This will process results and update HTML as appropriate. 
    xhr.onloadend = function () {
        console.log(xhr);
        console.log(xhr.request);

        if (xhr.readyState == XMLHttpRequest.DONE) {
            console.log("XHR:" + xhr.responseText);
            processUploadActivityResponse(xhr.responseText);
        } else {
            processUploadActivityResponse("N/A");
        }
    };
}

function processUploadActivityResponse(result) {
    var js = JSON.parse(result);
    var httpCode = js["httpCode"];
    console.log("upload activity" + httpCode);

    if (httpCode == 200) {
        console.log("upload activity successfully.");
    } else {
        console.log("upload activity not success");
    }
}

//添加顶级分类处理
function handleAddTopClassificationClick() {
    spinner.style.visibility = "visible";
    console.log("top classification");
    var form = document.addTopClassification;
    var arg1 = form.arg1.value;
    var arg2 = -1;
    var arg3 = 0;
    if (arg1 == null || arg1 == "") {
        alert("The input should not be empty.");
        return;
    }
    var data = {};
    data["arg1"] = arg1;
    data["arg2"] = arg2;
    data["arg3"] = arg3;

    var js = JSON.stringify(data);
    console.log("JS" + js);

    var xhr = new XMLHttpRequest();
    console.log(xhr);
    xhr.open("POST", addClassification_url, true);
    // send the collected data as JSON
    xhr.send(js);
    // This will process results and update HTML as appropriate. 
    xhr.onloadend = function () {
        console.log(xhr);
        console.log(xhr.request);

        if (xhr.readyState == XMLHttpRequest.DONE) {
            console.log("XHR:" + xhr.responseText);
            processAddclassificationResponse(xhr.responseText);
        } else {
            processAddclassificationResponse("N/A");
        }
    };
}

function handleAddSubClassificationClick() {
    spinner.style.visibility = "visible";
    var form = document.addSubClassification;
    var arg1 = form.arg1.value;
    var arg2 = fatherID;
    var arg3 = locatedLevel;

    if (arg1 == null || arg1 == "") {
        alert("The input should not be empty.");
        return;
    }
    var data = {};
    data["arg1"] = arg1;
    data["arg2"] = arg2;
    data["arg3"] = arg3 + 1;

    var js = JSON.stringify(data);
    console.log("JS" + js);

    var xhr = new XMLHttpRequest();
    console.log(xhr);
    xhr.open("POST", addClassification_url, true);
    // send the collected data as JSON
    xhr.send(js);
    // This will process results and update HTML as appropriate. 
    xhr.onloadend = function () {
        console.log(xhr);
        console.log(xhr.request);

        if (xhr.readyState == XMLHttpRequest.DONE) {
            console.log("XHR:" + xhr.responseText);
            processAddclassificationResponse(xhr.responseText);
        } else {
            processAddclassificationResponse("N/A");
        }
    };
}

//添加算法处理
function handleAddAlgorithmClick() {
    spinner.style.visibility = "visible";
    var form = document.addAlgorithm;
    var arg1 = form.arg1.value;
    var arg2 = form.arg2.value;
    var arg3 = fatherID;
    if (arg1 == null || arg1 == "") {
        alert("The input should not be empty.");
        return;
    }
    if (arg2 == null || arg2 == "") {
        alert("The input should not be empty.");
        return;
    }
    var data = {};
    data["arg1"] = arg1;
    data["arg2"] = arg3;
    data["arg3"] = arg2;

    var js = JSON.stringify(data);
    console.log("JS" + js);

    var xhr = new XMLHttpRequest();
    console.log(xhr);
    xhr.open("POST", addAlgorithm_url, true);
    // send the collected data as JSON
    xhr.send(js);
    // This will process results and update HTML as appropriate. 
    xhr.onloadend = function () {
        console.log(xhr);
        console.log(xhr.request);

        if (xhr.readyState == XMLHttpRequest.DONE) {
            console.log("XHR:" + xhr.responseText);
            processAddAlgorithmResponse(xhr.responseText);
        } else {
            processAddAlgorithmResponse("N/A");
        }
    };
}

//添加implementation
function handUploadImplementation() {
    spinner.style.visibility = "visible";
    var form = document.uploadImplementation;
    var arg1 = form.arg1.value;
    var arg2 = form.arg2.value;
    var arg3 = fatherID;
    console.log(fatherID);
    if (arg1 == null || arg1 == "") {
        alert("The input should not be empty.");
        return;
    }
    if (arg2 == null || arg2 == "") {
        alert("The input should not be empty.");
        return;
    }
    var data = {};
    data["arg1"] = arg1;
    data["arg2"] = arg2;
    data["arg3"] = arg3;

    var js = JSON.stringify(data);
    console.log("js" + js);

    var xhr = new XMLHttpRequest();
    console.log(xhr);
    xhr.open("POST", uploadImplementation_url, true)
    xhr.send(js);

    xhr.onloadend = function () {
        console.log(xhr);
        console.log(xhr.request);

        if (xhr.readyState == XMLHttpRequest.DONE) {
            console.log("XHR:" + xhr.responseText);
            processUploadImplementationResponse(xhr.responseText);
        } else {
            processUploadImplementationResponse("N/A");
        }
    };
    console.log("Upload Implementtion");
}