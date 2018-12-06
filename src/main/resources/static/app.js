var stompClient = null;


function updateGraph(data){
    console.log(data);
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (data) {
            updateGraph(JSON.parse(data.body).content);
        });
    });
}

function sendSetting() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
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
    });

    connect();
});