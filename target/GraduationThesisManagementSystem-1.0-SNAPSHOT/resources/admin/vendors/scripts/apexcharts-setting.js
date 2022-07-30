// var options5 = {
// 	series: [{
// 		name: 'Likes',
// 		data: [4, 3, 10, 9, 29, 19, 22, 9, 12, 7, 19, 5, 13, 9, 17, 2, 7, 5]
// 	}],
// 	chart: {
// 		height: 350,
// 		type: 'line',
// 		toolbar: {
// 			show: false,
// 		}
// 	},
// 	grid: {
// 		show: false,
// 		padding: {
// 			left: 0,
// 			right: 0
// 		}
// 	},
// 	stroke: {
// 		width: 7,
// 		curve: 'smooth'
// 	},
// 	xaxis: {
// 		type: 'datetime',
// 		categories: ['1/11/2020', '2/11/2020', '3/11/2020', '4/11/2020', '5/11/2020', '6/11/2020', '7/11/2020', '8/11/2020', '9/11/2020', '10/11/2020', '11/11/2020', '12/11/2020', '1/11/2021', '2/11/2021', '3/11/2021','4/11/2021' ,'5/11/2021' ,'6/11/2021'],
// 	},
// 	title: {
// 		text: 'Social Media',
// 		align: 'left',
// 		style: {
// 			fontSize: "16px",
// 			color: '#666'
// 		}
// 	},
// 	fill: {
// 		type: 'gradient',
// 		gradient: {
// 			shade: 'dark',
// 			gradientToColors: [ '#1b00ff'],
// 			shadeIntensity: 1,
// 			type: 'horizontal',
// 			opacityFrom: 1,
// 			opacityTo: 1,
// 			stops: [0, 100, 100, 100]
// 		},
// 	},
// 	markers: {
// 		size: 4,
// 		colors: ["#FFA41B"],
// 		strokeColors: "#fff",
// 		strokeWidth: 2,
// 		hover: {
// 			size: 7,
// 		}
// 	},
// 	yaxis: {
// 		min: -10,
// 		max: 40,
// 		title: {
// 			text: 'Engagement',
// 		},
// 	}
// };
// var chart = new ApexCharts(document.querySelector("#chart5"), options5);
// chart.render();
//
// var options6 = {
// 	series: [{
// 		name: 'Net Profit',
// 		data: [44, 55, 57, 56, 61, 58, 63, 60, 66]
// 	}, {
// 		name: 'Revenue',
// 		data: [76, 85, 101, 98, 87, 105, 91, 114, 94]
// 	}, {
// 		name: 'Free Cash Flow',
// 		data: [35, 41, 36, 26, 45, 48, 52, 53, 41]
// 	}],
// 	chart: {
// 		type: 'bar',
// 		height: 350,
// 		toolbar: {
// 			show: false,
// 		}
// 	},
// 	plotOptions: {
// 		bar: {
// 			horizontal: false,
// 			columnWidth: '25%',
// 			endingShape: 'rounded'
// 		},
// 	},
// 	dataLabels: {
// 		enabled: false
// 	},
// 	stroke: {
// 		show: true,
// 		width: 2,
// 		colors: ['transparent']
// 	},
// 	xaxis: {
// 		categories: ['Feb', 'Mar', 'Apr', 'May', 'Jun', 'Jul', 'Aug', 'Sep', 'Oct'],
// 	},
// 	yaxis: {
// 		title: {
// 			text: '$(thousands)'
// 		}
// 	},
// 	fill: {
// 		opacity: 1
// 	},
// 	tooltip: {
// 		y: {
// 			formatter: function (val) {
// 				return "$" + val + "thousands"
// 			}
// 		}
// 	}
// };
// var chart = new ApexCharts(document.querySelector("#chart6"), options6);
// chart.render();
