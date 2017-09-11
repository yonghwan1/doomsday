document.querySelector('#btn').addEventListener('click', function() {
  clickHandler();
});

window.addEventListener('load', function () {
  // loadHandler();
  init();
});

function init() {
    var str = "3 + 4 = " + chrome.extension.getBackgroundPage().sum(3,4);
    document.getElementById("text").innerHTML = str;
}

function loadHandler() {
    alert("test");
    chrome.notifications.create("test",
        {
            type:'basic',
            iconUrl: '../icon/icon64.png',
            title: "loadTest",
            message: "zzz_load"
        },
        function(notificationId) {}
    );
}
function clickHandler() {
    chrome.notifications.create("test",
        {
            type:'basic',
            iconUrl: '../icon/icon64.png',
            title: "clickTest",
            message: "zzz"
        },
        function(notificationId) {}
    );
}
