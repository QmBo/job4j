package ru.job4j.products;

import org.junit.Before;
import org.junit.Test;
import java.util.*;

import static org.junit.Assert.*;
import static org.hamcrest.core.Is.*;

public class ControlQualityTest {
    private Date now;
    private BreadFood pie;
    private BreadFood pieRep;
    private MilkFood cream;
    private MilkFood milk;
    private MilkFood cheese;
    private BreadFood coockes;
    private GreensFood tomato;

    @Before
    public void setUp() {
        Calendar calendar = new GregorianCalendar();
        this.now = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, -10);
        Date makeDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, 9);
        Date exDate = calendar.getTime();
        this.pie = new BreadFood("Mint Pie", exDate, makeDate, 100, false);
        this.pieRep = new BreadFood("Mint Pie", exDate, makeDate, 100, true);
        calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_MONTH, -10);
        makeDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, 18);
        exDate = calendar.getTime();
        this.cream = new MilkFood("cream", exDate, makeDate, 100, false);
        calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_MONTH, -10);
        makeDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, 11);
        exDate = calendar.getTime();
        this.milk = new MilkFood("milk", exDate, makeDate, 100, false);
        calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_MONTH, -10);
        makeDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, 200);
        exDate = calendar.getTime();
        this.cheese = new MilkFood("cheese", exDate, makeDate, 100, false);
        calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_MONTH, -10);
        makeDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, 500);
        exDate = calendar.getTime();
        this.coockes = new BreadFood("coockes", exDate, makeDate, 100, false);
        calendar = new GregorianCalendar();
        calendar.add(Calendar.DAY_OF_MONTH, -10);
        makeDate = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, 200);
        exDate = calendar.getTime();
        this.tomato = new GreensFood("cheese", exDate, makeDate, 100, false);
    }

    @Test
    public void whenFirstAddThenSort() {
        Warehouse warehouse = new Warehouse();
        warehouse.add(new HashSet<>(Arrays.asList(this.pie, this.pieRep, this.cream, this.milk, this.cheese, this.coockes)));
        Shop shop = new Shop();
        Trash trash = new Trash();
        Rework rework = new Rework();
        ControlQuality controlQuality = new ControlQuality(this.now, warehouse);
        List<Food> resW = controlQuality.checkQuality();
        controlQuality = new ControlQuality(this.now, shop);
        List<Food> resS = controlQuality.checkQuality(resW);
        controlQuality = new ControlQuality(this.now, trash);
        List<Food> resT = controlQuality.checkQuality(resS);
        controlQuality = new ControlQuality(this.now, rework);
        List<Food> resR = controlQuality.checkQuality(resT);
        assertThat(resR.isEmpty(), is(true));
        List<Food> shopRes = new LinkedList<>(shop.getStock());
        List<Food> wereRes = new LinkedList<>(warehouse.getStock());
        List<Food> trashRes = new LinkedList<>(trash.getStock());
        List<Food> reworkRes = new LinkedList<>(rework.getStock());
        Collections.sort(shopRes);
        Collections.sort(wereRes);
        assertThat(shopRes, is(Arrays.asList(this.cream, this.milk)));
        assertThat(wereRes, is(Arrays.asList(this.cheese, this.coockes)));
        assertThat(trashRes, is(Collections.singletonList(this.pie)));
        assertThat(reworkRes, is(Collections.singletonList(this.pieRep)));
        controlQuality = new ControlQuality(this.now, warehouse);
        resW = controlQuality.checkQuality(new LinkedList<>());
        controlQuality = new ControlQuality(this.now, shop);
        resS = controlQuality.checkQuality(resW);
        controlQuality = new ControlQuality(this.now, trash);
        resT = controlQuality.checkQuality(resS);
        controlQuality = new ControlQuality(this.now, rework);
        resR = controlQuality.checkQuality(resT);
        assertThat(resR.isEmpty(), is(true));
        assertThat(shopRes, is(Arrays.asList(this.cream, this.milk)));
        assertThat(wereRes, is(Arrays.asList(this.cheese, this.coockes)));
        assertThat(trashRes, is(Collections.singletonList(this.pie)));
        assertThat(reworkRes, is(Collections.singletonList(this.pieRep)));
    }

    @Test
    public void whenGreenFoodThenRefrigeratorGetIt() {
        Warehouse warehouse = new Warehouse();
        warehouse.add(new HashSet<>(Arrays.asList(this.cheese, this.coockes, this.tomato)));
        RefrigeratorWH refrigerator = new RefrigeratorWH();
        ControlQuality controlQuality = new ControlQuality(this.now, warehouse);
        List<Food> resW = controlQuality.checkQuality();
        controlQuality = new ControlQuality(this.now, refrigerator);
        List<Food> resRe = controlQuality.checkQuality(resW);
        assertThat(resRe.isEmpty(), is(true));
        List<Food> warehouseStock = new LinkedList<>(warehouse.getStock());
        List<Food> refrigeratorStock = new LinkedList<>(refrigerator.getStock());
        Collections.sort(warehouseStock);
        assertThat(warehouseStock, is(Arrays.asList(this.cheese, this.coockes)));
        assertThat(refrigeratorStock, is(Collections.singletonList(this.tomato)));
    }

    @Test
    public void whenReachedLimitThenNextWarehouse() {
        Warehouse firstWarehouse = new Warehouse(1);
        Warehouse secondWarehouse = new Warehouse();
        firstWarehouse.add(new HashSet<>(Arrays.asList(this.cheese, this.coockes)));
        ControlQuality controlQuality = new ControlQuality(this.now, firstWarehouse);
        List<Food> firstRes = controlQuality.checkQuality();
        assertThat(firstRes.size(), is(1));
        controlQuality = new ControlQuality(this.now, secondWarehouse);
        List<Food> secondRes = controlQuality.checkQuality(firstRes);
        assertThat(secondRes.isEmpty(), is(true));
    }
}