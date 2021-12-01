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
            <option value="point">Total Point</option>
            <option value="winTotal">Total Win</option>
            <option value="loseTotal">Total Lose</option>
        </select>
        <table class="table table-striped border">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Player</th>
                    <th scope="col">Total Point</th>
                    <th scope="col">Total Win</th>
                    <th scope="col">Total Lose</th>
                </tr>
            </thead>
            <tbody id="player-list">
            </tbody>
        </table>
    </div>
    <script src="js/index.js"></script>
</body>

</html>