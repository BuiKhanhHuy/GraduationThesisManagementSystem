const showStatsOnTable = (data) => {
  let idx = 0;
  let trSchoolYearHtml = "";
  let trScoreStatisticsHtml = "";
  let statsSchoolYearArea = document.getElementById("stats-school-year-area");
  let statsScoreArea = document.getElementById("stats-score-area");
  data.categories.map((schoolYear) => {
    trSchoolYearHtml += `<th scope="col" class="text-center">
                                ${schoolYear}
                            </th>`;
  });
  console.log(trSchoolYearHtml);

  for (let key in data.series) {
    if (key !== "") {
      let scoreHtml = "";
      data.series[key].map((value) => {
        scoreHtml += ` <td>${value === null ? "" : value}</td>`;
      });

      trScoreStatisticsHtml += `<tr>
                                            <td class="text-center">${++idx}</td>
                                            <td>${key}</td>
                                            ${scoreHtml}
                                        </tr>`;
    }
  }
  statsSchoolYearArea.insertAdjacentHTML("beforeend", trSchoolYearHtml);
  statsScoreArea.innerHTML = trScoreStatisticsHtml;
};
