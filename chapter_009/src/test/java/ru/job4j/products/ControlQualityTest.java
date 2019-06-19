package ru.job4j.products;

import org.junit.Before;
import org.junit.Test;
import java.util.*;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

public class ControlQualityTest {
    public Date now;
    public BreadFood pie;
    public MilkFood cream;
    public MilkFood milk;
    public MilkFood cheese;
    public BreadFood coockes;

    @Before
    public void setUp() {
        Calendar calendar = new GregorianCalendar();
        this.now = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, -10);
        Date makeDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, 9);
        Date exDate = calendar.getTime();
        this.pie = new BreadFood("Mint Pie", exDate, makeDate, 100);
        calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_MONTH, -10);
        makeDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, 18);
        exDate = calendar.getTime();
        this.cream = new MilkFood("cream", exDate, makeDate, 100);
        calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_MONTH, -10);
        makeDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, 11);
        exDate = calendar.getTime();
        this.milk = new MilkFood("milk", exDate, makeDate, 100);
        calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_MONTH, -10);
        makeDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, 200);
        exDate = calendar.getTime();
        this.cheese = new MilkFood("cheese", exDate, makeDate, 100);
        calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_MONTH, -10);
        makeDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, 500);
        exDate = calendar.getTime();
        this.coockes = new BreadFood("coockes", exDate, makeDate, 100);
    }

    @Test
    public void whenFirstAddThenSort() {
        Warehouse warehouse = new Warehouse();
        warehouse.add(new HashSet<>(Arrays.asList(this.pie, this.cream, this.milk, this.cheese, this.coockes)));
        Shop shop = new Shop();
        Trash trash = new Trash();
        ControlQuality controlQuality = new ControlQuality(this.now, warehouse, shop, trash);
        controlQuality.checkQuality();
        this.milk.setDiscount(50D);
        List<Food> shopRes = new LinkedList<>(shop.getStock());
        List<Food> wereRes = new LinkedList<>(warehouse.getStock());
        List<Food> trashRes = new LinkedList<>(trash.getStock());
        Collections.sort(shopRes);
        Collections.sort(wereRes);
        Collections.sort(trashRes);
        assertThat(shopRes, is(Arrays.asList(this.cream, this.milk)));
        assertThat(wereRes, is(Arrays.asList(this.cheese, this.coockes)));
        assertThat(trashRes, is(Collections.singletonList(this.pie)));
        controlQuality.checkQuality();
        assertThat(shopRes, is(Arrays.asList(this.cream, this.milk)));
        assertThat(wereRes, is(Arrays.asList(this.cheese, this.coockes)));
        assertThat(trashRes, is(Collections.singletonList(this.pie)));
    }
}