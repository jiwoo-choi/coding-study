import DateBuilder from "./DateBuilder";
import DateManager from "./DateManager";
export default class $ {
    static get datebuilder() : DateBuilder {
        return DateBuilder.getInstance();
    }
    static get date(): DateManager {
        return DateManager.getInstance();
    }
}
