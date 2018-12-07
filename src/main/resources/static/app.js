var stompClient = null;
var plotData = [];

function updateGraph(data) {
    console.log("Got data from server: " + data);
    var ind = 0;
    var array = [];
    var xUpdates = [];
    var yUpdates = [];
    var init = Object.keys(plotData).length === 0;
    for (var agent in data) {
        if (!plotData.hasOwnProperty(agent)) {
            plotData[agent] = {
                x: [],
                y: [],
                mode: 'lines+markers',
                name: agent
            };
        }
        plotData[agent].x.push(data[agent].x);
        plotData[agent].y.push(data[agent].y);
        xUpdates.push([data[agent].x]);
        yUpdates.push([data[agent].y]);
        array.push(ind++);
    }
    if (init) {
        Plotly.newPlot('plot', Object.values(plotData), {});
        return;
    }
    Plotly.extendTraces('plot', {x: xUpdates, y: yUpdates}, array);
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
    Plotly.newPlot('plot', [], {});
}

function sendSetting() {
    stompClient.send("/app/settings", {}, JSON.stringify({
        'numAgents': $("#a1").val(),
        'angleSettings': {
            'angleSamplingStrategy': $("#a4").val(),
            'maxAnglePerFrame': $("#a2").val(),
            'minAnglePerFrame': $("#a3").val(),
        },
        'speedSettings': {
            'maxSpeedPerFrame': $("#a5").val(),
            'minSpeedPerFrame': $("#a6").val(),
            'speedSamplingStrategy': $("#a7").val(),
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