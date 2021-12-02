getSortedStatistic("point");

const ctx = document.getElementById("myChart").getContext("2d");
const myChart = new Chart(ctx, {
  type: "line",
  data: {
    labels: [],
    datasets: [
      {
        label: "",
        data: [],
        backgroundColor: "rgba(255, 99, 132, 0.2)",
        borderColor: "rgba(255, 99, 132, 0.2)",
        borderWidth: 3,
      },
    ],
  },
  options: {
    scales: {
      y: {
        beginAtZero: true,
      },
    },
  },
});

function updateChart(chart, listLabels, listData, label) {
  Chart.defaults.font.size = 16;
  chart.data.labels = listLabels;
  chart.data.datasets[0].label = label;
  chart.data.datasets[0].data = listData;
  chart.update();
}

function moveToMatchHistoryPage(id) {
  window.location.href += "match_history.php";
  window.localStorage.setItem("id", id);
}

async function getSortedStatistic(option) {
  const xhttp = new XMLHttpRequest();
  xhttp.onreadystatechange = function () {
    if ((xhttp.readyState = 4 && xhttp.status == 200))
      document.getElementById("player-list").innerHTML = xhttp.responseText;
  };
  xhttp.open("GET", "php/get_data.php?action=sort&option=" + option, true);
  xhttp.send();
  window
    .fetch(`php/get_data.php?action=get_json_data`)
    .then((response) => response.json())
    .then((data) => {
      const listLabels = Array.from(data, (item) => item.username);
      let listData;
      let label;
      if (option === "point") {
        listData = Array.from(data, (item) => item.point);
        label = "Total Point";
      } else if (option === "winTotal") {
        listData = Array.from(data, (item) => item.winTotal);
        label = "Total Win";
      } else {
        listData = Array.from(data, (item) => item.loseTotal);
        label = "Total Lose";
      }
      updateChart(myChart, listLabels, listData, label);
    })
    .catch((error) => {
      console.log("error", error);
    });
}
