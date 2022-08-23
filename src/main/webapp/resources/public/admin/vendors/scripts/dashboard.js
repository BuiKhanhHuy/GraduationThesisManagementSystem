var options = {
    series: [80],
    grid: {
        padding: {
            top: 0,
            right: 0,
            bottom: 0,
            left: 0
        },
    },
    chart: {
        height: 100,
        width: 70,
        type: 'radialBar',
    },
    plotOptions: {
        radialBar: {
            hollow: {
                size: '50%',
            },
            dataLabels: {
                name: {
                    show: false,
                    color: '#fff'
                },
                value: {
                    show: true,
                    color: '#333',
                    offsetY: 5,
                    fontSize: '15px'
                }
            }
        }
    },
    colors: ['#ecf0f4'],
    fill: {
        type: 'gradient',
        gradient: {
            shade: 'dark',
            type: 'diagonal1',
            shadeIntensity: 0.8,
            gradientToColors: ['#1b00ff'],
            inverseColors: false,
            opacityFrom: [1, 0.2],
            opacityTo: 1,
            stops: [0, 100],
        }
    },
    states: {
        normal: {
            filter: {
                type: 'none',
                value: 0,
            }
        },
        hover: {
            filter: {
                type: 'none',
                value: 0,
            }
        },
        active: {
            filter: {
                type: 'none',
                value: 0,
            }
        },
    }
};

var options2 = {
    series: [70],
    grid: {
        padding: {
            top: 0,
            right: 0,
            bottom: 0,
            left: 0
        },
    },
    chart: {
        height: 100,
        width: 70,
        type: 'radialBar',
    },
    plotOptions: {
        radialBar: {
            hollow: {
                size: '50%',
            },
            dataLabels: {
                name: {
                    show: false,
                    color: '#fff'
                },
                value: {
                    show: true,
                    color: '#333',
                    offsetY: 5,
                    fontSize: '15px'
                }
            }
        }
    },
    colors: ['#ecf0f4'],
    fill: {
        type: 'gradient',
        gradient: {
            shade: 'dark',
            type: 'diagonal1',
            shadeIntensity: 1,
            gradientToColors: ['#009688'],
            inverseColors: false,
            opacityFrom: [1, 0.2],
            opacityTo: 1,
            stops: [0, 100],
        }
    },
    states: {
        normal: {
            filter: {
                type: 'none',
                value: 0,
            }
        },
        hover: {
            filter: {
                type: 'none',
                value: 0,
            }
        },
        active: {
            filter: {
                type: 'none',
                value: 0,
            }
        },
    }
};

var options3 = {
    series: [75],
    grid: {
        padding: {
            top: 0,
            right: 0,
            bottom: 0,
            left: 0
        },
    },
    chart: {
        height: 100,
        width: 70,
        type: 'radialBar',
    },
    plotOptions: {
        radialBar: {
            hollow: {
                size: '50%',
            },
            dataLabels: {
                name: {
                    show: false,
                    color: '#fff'
                },
                value: {
                    show: true,
                    color: '#333',
                    offsetY: 5,
                    fontSize: '15px'
                }
            }
        }
    },
    colors: ['#ecf0f4'],
    fill: {
        type: 'gradient',
        gradient: {
            shade: 'dark',
            type: 'diagonal1',
            shadeIntensity: 0.8,
            gradientToColors: ['#f56767'],
            inverseColors: false,
            opacityFrom: [1, 0.2],
            opacityTo: 1,
            stops: [0, 100],
        }
    },
    states: {
        normal: {
            filter: {
                type: 'none',
                value: 0,
            }
        },
        hover: {
            filter: {
                type: 'none',
                value: 0,
            }
        },
        active: {
            filter: {
                type: 'none',
                value: 0,
            }
        },
    }
};

var options4 = {
    series: [85],
    grid: {
        padding: {
            top: 0,
            right: 0,
            bottom: 0,
            left: 0
        },
    },
    chart: {
        height: 100,
        width: 70,
        type: 'radialBar',
    },
    plotOptions: {
        radialBar: {
            hollow: {
                size: '50%',
            },
            dataLabels: {
                name: {
                    show: false,
                    color: '#fff'
                },
                value: {
                    show: true,
                    color: '#333',
                    offsetY: 5,
                    fontSize: '15px'
                }
            }
        }
    },
    colors: ['#ecf0f4'],
    fill: {
        type: 'gradient',
        gradient: {
            shade: 'dark',
            type: 'diagonal1',
            shadeIntensity: 0.8,
            gradientToColors: ['#2979ff'],
            inverseColors: false,
            opacityFrom: [1, 0.5],
            opacityTo: 1,
            stops: [0, 100],
        }
    },
    states: {
        normal: {
            filter: {
                type: 'none',
                value: 0,
            }
        },
        hover: {
            filter: {
                type: 'none',
                value: 0,
            }
        },
        active: {
            filter: {
                type: 'none',
                value: 0,
            }
        },
    }
};

var chart = new ApexCharts(document.querySelector("#chart"), options);
chart.render();

var chart2 = new ApexCharts(document.querySelector("#chart2"), options2);
chart2.render();

var chart3 = new ApexCharts(document.querySelector("#chart3"), options3);
chart3.render();

var chart4 = new ApexCharts(document.querySelector("#chart4"), options4);
chart4.render();

const thesisScoreStatistics = (type = "spline", data) => {
    let series = []
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

