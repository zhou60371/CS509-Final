var spinner = document.getElementById("spinner");
var mask = document.getElementsByClassName("mask")[0];
var implementation;
var problemInstanceID;
$(document).ready(handleGetImplementation);

function processGetImplementationResponse(result) {
    console.log("result:" + result);
    var js = JSON.parse(result);

    var imple = js["imple"];
    implementation = imple;
    var httpCode = js["httpCode"];
    var error = js["error"];

    if (httpCode == 200) {
        $("#algorithmName").text(imple.algo.name);
        $("#algorithmDescription").text(imple.algo.description);
        $("#impleName").text(imple.language + " implementation");
        $("#impleContent").text(imple.content);
    } else {
        alert(error);
    }
    handleGetProblemInstance();
}

function handleGetImplementation() {
    mask.style.display = "block";
    window.addEventListener("scroll", function (e) {
        $("div.mask").css("top", $(document).scrollTop() - 300 + "px");
    })
    console.log("加载成功");
    console.log(window.location.search)
    var searchParams = new URLSearchParams(window.location.search);
    console.log(searchParams.get("id"));

    var id = searchParams.get("id");
    var data = {};
    data["id"] = id;

    var js = JSON.stringify(data);
    console.log("js", js);

    var xhr = new XMLHttpRequest();
    console.log(xhr);
    xhr.open("POST", getImplementation_url, true)
    xhr.send(js);

    xhr.onloadend = function () {
        // console.log(xhr);
        // console.log(xhr.request);

        if (xhr.readyState == XMLHttpRequest.DONE) {
            console.log("XHR:" + xhr.responseText);
            processGetImplementationResponse(xhr.responseText);
        } else {
            processGetImplementationResponse("N/A");
        }
    };
    console.log("Get Implementtion");
}

function handleGetProblemInstance() {
    var data = {};
    data["id"] = implementation.algo.id;
    var js = JSON.stringify(data);

    var xhr = new XMLHttpRequest();
    xhr.open("POST", getProblemInstance_url, true);
    xhr.send(js);

    xhr.onloadend = function () {
        // console.log(xhr);
        // console.log(xhr.request);

        if (xhr.readyState == XMLHttpRequest.DONE) {
            processProblemInstanceResponse(xhr.responseText);
        } else {
            processProblemInstanceResponse("N/A");
        }
    };
}

function handlerAddBenchmark() {
    spinner.style.visibility = "visible";
    let form = document.addBenchmark;
    let cpu = form.cpu.value;
    let thread = form.thread.value;
    let cores = form.thread.value;
    let L1 = form.L1.value;
    let L2 = form.L2.value;
    let L3 = form.L3.value;
    let cache = form.cache.value;
    let runningTime = form.runningTime.value;
    let imple = implementation.id;
    let problemInstance = problemInstanceID;

    var d = new Date();
    var year = d.getFullYear();
    var month = d.getMonth();
    var day = d.getDate();
    var hours = d.getHours();
    var minites = d.getMinutes();
    var date = "" + year + "-" + month + "-" + day + " " + hours + ":" + minites;

    if (cpu == "" || thread == "" || cores == "" || L1 == "" || L2 == "" || L3 == "" || cache == "" || runningTime == "") {
        alert("The form has emty areas. You must fill every input area.");
        mask.style.display = "none";
        return;
    }

    var data = {};
    data["cpu"] = cpu;
    data["date"] = date;
    data["threads"] = thread;
    data["cores"] = cores;
    data["L1"] = L1;
    data["L2"] = L2;
    data["L3"] = L3;
    data["cache"] = cache;
    data["runningTime"] = runningTime;
    data["imple"] = imple;
    data["problemInstance"] = problemInstance

    var js = JSON.stringify(data);

    var xhr = new XMLHttpRequest();
    xhr.open("POST", uploadBenchmark_url, true);
    xhr.send(js);

    xhr.onloadend = function () {
        // console.log(xhr);
        // console.log(xhr.request);

        if (xhr.readyState == XMLHttpRequest.DONE) {
            processUploadBenchmarkResponse(xhr.responseText);
        } else {
            processUploadBenchmarkResponse("N/A");
        }
    };
}

function processProblemInstanceResponse(result) {
    console.log("result:" + result);
    var js = JSON.parse(result);
    var httpCode = js["httpCode"];
    var error = js["error"];
    if (httpCode == 200) {
        document.getElementsByClassName("containerBenckmark")[0].innerHTML = "";
        var pis = js["pis"];
        if (pis != undefined) {
            for (var key in pis) {
                let div_benchmark = document.createElement("div");
                div_benchmark.setAttribute("class", "col-md-8 offset-md-2 shadow p-3 mb-5 bg-body rounded");
                let p = document.createElement("p");
                let i = document.createElement("i");
                i.setAttribute("class", "bi bi-person-circle")
                let span = document.createElement("span");
                span.innerHTML = key;
                p.appendChild(i);
                p.appendChild(span);
                div_benchmark.appendChild(p);
                for (let i = 0; i < pis[key].length; i++) {
                    if (pis[key][i].caseType == 0) {
                        let div = document.createElement("div");
                        div.style.paddingBottom = "5px";
                        let span = document.createElement('span');
                        let a = document.createElement("a");
                        let icon = document.createElement("i");
                        let button = document.createElement("button");
                        icon.setAttribute("class", "bi bi-file-earmark-text");
                        a.setAttribute("href", pis[key][i].fileAdress);
                        a.appendChild(icon);
                        span.innerHTML = "Best Case:";
                        button.innerHTML = "Add Benchemark";
                        button.setAttribute("type", "button");
                        button.setAttribute("class", "btn btn-primary addBkBtn");
                        button.setAttribute("data-probleminstanceid", pis[key][i].id);
                        button.style.marginLeft = "40px";
                        div.appendChild(span);
                        div.appendChild(a);
                        div.appendChild(button);
                        let div_outer_row = document.createElement("div");
                        div_outer_row.setAttribute("class", "row");
                        for (let j = 0; j < pis[key][i].benchmarks.length; j++) {
                            let div_row = document.createElement("div");
                            div_row.setAttribute("class", "row");
                            let div_xx = document.createElement("div");
                            div_xx.setAttribute("class", "xx col-md-4 rounded-3 bg-dark text-light");
                            let div_1 = document.createElement("div");
                            div_1.innerHTML = "CPU: " + pis[key][i].benchmarks[j].cpu;
                            div_1.setAttribute("class", "col-md-4");
                            let div_2 = document.createElement("div");
                            div_2.innerHTML = "L1: " + pis[key][i].benchmarks[j].L1;
                            div_2.setAttribute("class", "col-md-4");
                            let div_3 = document.createElement("div");
                            div_3.innerHTML = "L2: " + pis[key][i].benchmarks[j].L2;
                            div_3.setAttribute("class", "col-md-4");
                            let div_4 = document.createElement("div");
                            div_4.innerHTML = "L3: " + pis[key][i].benchmarks[j].L3;
                            div_4.setAttribute("class", "col-md-4");
                            let div_5 = document.createElement("div");
                            div_5.innerHTML = "Threads: " + pis[key][i].benchmarks[j].threads;
                            div_5.setAttribute("class", "col-md-4");
                            let div_6 = document.createElement("div");
                            div_6.innerHTML = "Cores: " + pis[key][i].benchmarks[j].cores;
                            div_6.setAttribute("class", "col-md-4");
                            let div_7 = document.createElement("div");
                            div_7.innerHTML = "Cache: " + pis[key][i].benchmarks[j].cache;
                            div_7.setAttribute("class", "col-md-4");
                            let div_8 = document.createElement("div");
                            div_8.innerHTML = "Date: " + pis[key][i].benchmarks[j].date;
                            div_8.setAttribute("class", "col-md-4");
                            let div_9 = document.createElement("div");
                            div_9.innerHTML = "Running TIme: " + pis[key][i].benchmarks[j].runingTime;
                            div_9.setAttribute("class", "col-md-4");
                            div_row.appendChild(div_1);
                            div_row.appendChild(div_2);
                            div_row.appendChild(div_3);
                            div_row.appendChild(div_4);
                            div_row.appendChild(div_5);
                            div_row.appendChild(div_6);
                            div_row.appendChild(div_7);
                            div_row.appendChild(div_8);
                            div_row.appendChild(div_9);
                            div_xx.appendChild(div_row);
                            div_outer_row.appendChild(div_xx);
                        }
                        div.appendChild(div_outer_row);
                        div_benchmark.appendChild(div);
                    } else if (pis[key][i].caseType == 1) {
                        let div = document.createElement("div");
                        let span = document.createElement('span');
                        let a = document.createElement("a");
                        let icon = document.createElement("i");
                        let button = document.createElement("button");
                        icon.setAttribute("class", "bi bi-file-earmark-text");
                        a.setAttribute("href", pis[key][i].fileAdress);
                        a.appendChild(icon);
                        span.innerHTML = "Worst Case: ";
                        button.innerHTML = "Add Benchemark";
                        button.setAttribute("type", "button");
                        button.setAttribute("class", "btn btn-primary addBkBtn");
                        button.setAttribute("data-probleminstanceid", pis[key][i].id);
                        button.style.marginLeft = "40px";
                        div.appendChild(span);
                        div.appendChild(a);
                        div.appendChild(button);
                        let div_outer_row = document.createElement("div");
                        div_outer_row.setAttribute("class", "row");
                        for (let j = 0; j < pis[key][i].benchmarks.length; j++) {
                            let div_row = document.createElement("div");
                            div_row.setAttribute("class", "row");
                            let div_xx = document.createElement("div");
                            div_xx.setAttribute("class", "xx col-md-4 rounded-3 bg-dark text-light");
                            let div_1 = document.createElement("div");
                            div_1.innerHTML = "CPU: " + pis[key][i].benchmarks[j].cpu;
                            div_1.setAttribute("class", "col-md-4");
                            let div_2 = document.createElement("div");
                            div_2.innerHTML = "L1: " + pis[key][i].benchmarks[j].L1;
                            div_2.setAttribute("class", "col-md-4");
                            let div_3 = document.createElement("div");
                            div_3.innerHTML = "L2: " + pis[key][i].benchmarks[j].L2;
                            div_3.setAttribute("class", "col-md-4");
                            let div_4 = document.createElement("div");
                            div_4.innerHTML = "L3: " + pis[key][i].benchmarks[j].L3;
                            div_4.setAttribute("class", "col-md-4");
                            let div_5 = document.createElement("div");
                            div_5.innerHTML = "Threads: " + pis[key][i].benchmarks[j].threads;
                            div_5.setAttribute("class", "col-md-4");
                            let div_6 = document.createElement("div");
                            div_6.innerHTML = "Cores: " + pis[key][i].benchmarks[j].cores;
                            div_6.setAttribute("class", "col-md-4");
                            let div_7 = document.createElement("div");
                            div_7.innerHTML = "Cache: " + pis[key][i].benchmarks[j].cache;
                            div_7.setAttribute("class", "col-md-4");
                            let div_8 = document.createElement("div");
                            div_8.innerHTML = "Date: " + pis[key][i].benchmarks[j].date;
                            div_8.setAttribute("class", "col-md-4");
                            let div_9 = document.createElement("div");
                            div_9.innerHTML = "Running TIme: " + pis[key][i].benchmarks[j].runingTime;
                            div_9.setAttribute("class", "col-md-4");
                            div_row.appendChild(div_1);
                            div_row.appendChild(div_2);
                            div_row.appendChild(div_3);
                            div_row.appendChild(div_4);
                            div_row.appendChild(div_5);
                            div_row.appendChild(div_6);
                            div_row.appendChild(div_7);
                            div_row.appendChild(div_8);
                            div_row.appendChild(div_9);
                            div_xx.appendChild(div_row);
                            div_outer_row.appendChild(div_xx);
                        }
                        div.appendChild(div_outer_row);
                        div_benchmark.appendChild(div);
                    }
                }
                $(".containerBenckmark").append(div_benchmark);
            }
        }
        mask.style.display = "none";
        spinner.style.visibility = "hidden";
    } else {
        alert("Fail Get problemInstnace");
        mask.style.display = "none";
        spinner.style.visibility = "hidden";
    }

    $("button.addBkBtn").off("click").on("click", function (e) {
        $("div.addbenchmark").css({ "display": "block", "z-index": "3" });
        $("div.addbenchmark").css("top", $(document).scrollTop() + 400 + "px");
        mask.style.display = "block";
        problemInstanceID = $(e.target).data("probleminstanceid");
    })

    $("div.addbenchmark button.addBkCancel").off("click").on("click", function (e) {
        $(".addbenchmark").css({ "display": "none", "z-index": "-3" });
        mask.style.display = "none";
    })

    $("div.addbenchmark button.addBkSubmit").off("click").on("click", function (e) {
        $(".addbenchmark").css({ "display": "none", "z-index": "-3" });
        // mask.style.display = "none";
        handlerAddBenchmark();
        let form = document.addBenchmark;
        form.cpu.value = "";
        form.thread.value = "";
        form.thread.value = "";
        form.L1.value = "";
        form.L2.value = "";
        form.L3.value = "";
        form.cache.value = "";
        form.runningTime.value = "";
    })
}

function processUploadBenchmarkResponse(result) {

    console.log("result:" + result);
    var js = JSON.parse(result);
    var httpCode = js["httpCode"];
    var response = js["response"];
    if (httpCode == 200) {
        console.log("successfully upload a benmark");
        handleGetProblemInstance();
    } else {
        alert("fail to upload a benchmark");
        spinner.style.visibility = "hidden";
        mask.style.display = "none";
    }
}
