package stocks

class TestController {

    def index() {}

    def draggableChart() {
        [
                categories: ['2007 (Actuals)', '2008 (Actuals)', '2009 (Actuals)', '2010 (Actuals)', '2011 (Actuals)', '2012 (Actuals)', '2013 (Actuals)', '2014 (Actuals)'],
                trendCategories: ['2015 (Predicted)', '2016 (Predicted)', '2017 (Predicted)'],
                series: [
                        [
                                name: 'Inkjet Printers',
                                data: [25, 20, 17, 35, 57, 41, 45, 87]
                        ],
                        [
                                name: 'Laser Printers',
                                data: [57, 41, 45, 87, 45, 44, 18, 77]
                        ],
                        [
                                name: 'Cartridges',
                                data: [45, 44, 18, 77, 25, 20, 17, 35]
                        ]
                ]
        ]
    }

    def draggableChartJson() {

    }
}