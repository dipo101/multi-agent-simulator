var stompClient = null;
var plotData = [];

function updateGraph(data){
    console.log("Got data from server: " + data);

    for(var agent in data){
        if (!plotData.hasOwnProperty(agent)){
            plotData[agent] = {
                x:[],
                y:[],
                mode: 'lines+markers'
            };
        }
        plotData[agent].x.push(data[agent].x);
        plotData[agent].y.push(data[agent].y);
    }

    var layout = {};
    Plotly.update('plot', Object.values(plotData), layout);
}

function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/new-coords', function (data) {
            updateGraph(JSON.parse(data.body));
        });
    });
    plotData = [];
    Plotly.newPlot('plot', plotData, {});
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