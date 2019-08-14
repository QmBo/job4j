package ru.job4j.products;

import java.util.LinkedList;
import java.util.List;
/**
 * RControlQuality
 * @author Victor Egorov (qrioflat@gmail.com).
 * @version 0.1
 * @since 14.08.2019
 */
public class RControlQuality {
    /**
     * Control Quality.
     */
    private final ControlQuality controlQuality;
    /**
     * Rework store.
     */
    private final Rework rework;

    /**
     * Constructor.
     * @param controlQuality control quality.
     * @param rework rework store.
     */
    public RControlQuality(final ControlQuality controlQuality, final Rework rework) {
        this.controlQuality = controlQuality;
        this.rework = rework;
    }

    /**
     * Quality check.
     */
    public List<Food> checkQuality() {
        return this.checkQuality(new LinkedList<>());
    }

    /**
     * Quality check.
     */
    public List<Food> checkQuality(List<Food> inputFood) {
        List<Food> result = new LinkedList<>();
        List<Food> notAccept = new LinkedList<>();
        inputFood.addAll(this.controlQuality.stock.removeAll());
        inputFood.addAll(this.rework.removeAll());
        inputFood.forEach(food -> {
            if (this.rework.accept(food, this.controlQuality.date)) {
                this.rework.add(food);
            } else {
                notAccept.add(food);
            }
        });
        if (!notAccept.isEmpty()) {
            result = this.controlQuality.checkQuality(notAccept);
        }
        return result;
    }
}
