const thesisScoreStatistics = (type = "spline", data) => {
    let series = []
    console.log(data)
    for (let name in data.series) {
        if (name !== '') {
            let r = Math.random() * 255
            let g = Math.random() * 255
            let b = Math.random() * 255

            series.push({
                'name': name,
                'color': `rgba(${r}, ${g}, ${b}, 1)`,
                'marker': {
                    'symbol': 'circle'
                },
                'data': data.series[name]
            })
        }
    }
    console.log(series)


    Highcharts.chart('chart5', {
        chart: {
            type: 'line'
        },
        title: {
            text: ''
        },
        xAxis: {
            categories: data.categories,
            labels: {
                style: {
                    color: '#1b00ff',
                    fontSize: '12px',
                }
            }
        },
        yAxis: {
            labels: {
                formatter: function () {
                    return this.value + " điểm";
                },
                style: {
                    color: '#1b00ff',
                    fontSize: '14px'
                }
            },
            max: 10,
            title: {
                text: 'Điểm trung bình khóa luận từng ngành qua các niên khóa'
            },
        },
        credits: {
            enabled: false
        },
        tooltip: {
            crosshairs: true,
            shared: true,
        },
        plotOptions: {
            spline: {
                marker: {
                    radius: 10,
                    lineColor: '#1b00ff',
                    lineWidth: 2
                }
            }
        },
        legend: {
            align: 'center',
            x: 0,
            y: 0
        },
        series: series
    });
}

const thesisStatisticsByMajor = (type = "bar", labels = [], data = []) => {
    var options6 = {
        series: [{
            name: 'Tần suất tham gia khóa luận',
            data: data,
        }],
        chart: {
            type: type,
            height: 350,
            toolbar: {
                show: false,
            }
        },
        plotOptions: {
            bar: {
                horizontal: false,
                columnWidth: '25%',
                endingShape: 'rounded',
                distributed: true
            },
        },
        dataLabels: {
            enabled: false
        },
        stroke: {
            show: true,
            width: 2,
            colors: ['transparent']
        },
        xaxis: {
            categories: labels,
        },
        yaxis: {
            title: {
                text: 'Số lượng khóa luận'
            }
        },
        fill: {
            opacity: 1
        },
        tooltip: {
            y: {
                formatter: function (val) {
                    return val + " khóa luận"
                }
            }
        }
    };

    var chart6 = new ApexCharts(document.querySelector("#chart6"), options6);
    chart6.render();
}

