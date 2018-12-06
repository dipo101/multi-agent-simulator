var stompClient = null;


function updateGraph(data){
    console.log("Got data from server: " + data);
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/new-coords', function (data) {
            updateGraph(JSON.parse(data.body).content);
        });
    });
}

function sendSetting() {
    stompClient.send("/app/settings", {}, JSON.stringify({
        'numAgents': $("#a1").val(),
        'angleSettings': {
            'angleSamplingStrategy':$("#a4").val(),
            'maxAnglePerFrame':$("#a2").val(),
            'minAnglePerFrame':$("#a3").val(),
        },
        'speedSettings': {
            'maxSpeedPerFrame':$("#a5").val(),
            'minSpeedPerFrame':$("#a6").val(),
            'speedSamplingStrategy':$("#a7").val(),
        }

    }));
}

function getNewCoord() {
    stompClient.send("/app/next-coords", {}, JSON.stringify());
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
        sendSetting();
        setInterval(getNewCoord, 1000);
    });

    connect();
});