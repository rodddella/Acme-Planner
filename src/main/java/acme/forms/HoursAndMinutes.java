package acme.forms;


public class HoursAndMinutes {
	static final Double DECIMAL_CONVERTER = 1 / 0.6;

    private final Integer hours;
    private final Integer minutes;

    public static HoursAndMinutes fromDecimalTime(final Double decimalTime) {
        final Integer hours = (int) Math.floor(decimalTime);
        final Integer minutes = (int) Math.round((decimalTime - Math.floor(decimalTime)) / HoursAndMinutes.DECIMAL_CONVERTER * 100);
        return new HoursAndMinutes(hours, minutes);
    }

    public static HoursAndMinutes fromFormattedTime(final Double formattedTime) throws Exception {
        final Integer hours = (int) Math.floor(formattedTime);
        final Integer minutes = (int) Math.round((formattedTime - Math.floor(formattedTime)) * 100);
        if (minutes >= 60) {
            throw new Exception("Input range is invalid");
        }
        return new HoursAndMinutes(hours, minutes);
    }

    public static HoursAndMinutes fromHoursAndMinutes(final Integer hours, final Integer minutes) throws Exception {
        if (minutes >= 60) {
            throw new Exception("Input range is invalid");
        }
        return new HoursAndMinutes(hours, minutes);
    }

    public static HoursAndMinutes empty() {
        return new HoursAndMinutes(0, 0);
    }

    private HoursAndMinutes(final Integer hours, final Integer minutes) {
        super();
        this.hours = hours;
        this.minutes = minutes;
    }

    public Integer getHours() {
        return this.hours;
    }

    public Integer getMinutes() {
        return this.minutes;
    }

    public Double getDecimalTime() {
        return this.hours + (double) this.minutes / 100 * HoursAndMinutes.DECIMAL_CONVERTER;
    }

    public Double getFormattedTime() {
        return this.hours + (double) this.minutes / 100;
    }

    @Override
    public String toString() {
        if (this.hours >= 24) {
            return String.format("%dd %dh %dmin", this.hours / 24, this.hours % 24, this.minutes);
        } else {
            return String.format("%dh %dmin", this.hours, this.minutes);
        }
    }
}
