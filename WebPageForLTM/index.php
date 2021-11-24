<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet"
        integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous" />
    <title>Statistics</title>
</head>

<body>
    <div class="container">
        <h3 class="text-center mb-5">Statistics Game "3 cay"</h3>
        <select class="form-select mb-4" aria-label="Default select example" onChange="getSortedStatistic(this.value)">
            <option value="score">Score</option>
            <option value="1stInGameCount">1st In Game</option>
            <option value="2ndInGameCount">2nd In Game</option>
            <option value="3rdInGameCount">3rd In Game</option>
            <option value="4thInGameCount">4th In Game</option>
        </select>
        <table class="table table-striped border">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Player</th>
                    <th scope="col">Score</th>
                    <th scope="col">1st In Game Total</th>
                    <th scope="col">2nd In Game Total</th>
                    <th scope="col">3rd In Game Total</th>
                    <th scope="col">4th In Game Total</th>
                </tr>
            </thead>
            <tbody id="player__list">
            </tbody>
        </table>
    </div>
    <script src="js/index.js"></script>
    <script src="js/matchHistory.js"></script>
</body>

</html>