import { README } from "../../properties/path";
import { ChartGeneratorService, TableGeneratorService } from "service";
import { Calendar } from "../../util";
import fs from 'fs';

export interface DashboardManageService {
    updateDashboard():void;
}

export default class DashboardManager implements DashboardManageService {

    private tableGenerator : TableGeneratorService;
    private chartGenerator : ChartGeneratorService;
    private dateManager : Calendar;

    constructor(tableGenerator : TableGeneratorService, chartGenerator: ChartGeneratorService, dateManager: Calendar) {
        this.tableGenerator = tableGenerator;
        this.chartGenerator = chartGenerator;
        this.dateManager = dateManager;
    }

    updateDashboard(): void {
        let content = "";
        content += this.chartGenerator.populateChart();
        content += this.tableGenerator.populateTable("✅", " ");
        content += '\n';
        content += this.dateManager.builder("-").year.month.day.build();
        fs.writeFileSync(README, content);
    }

}