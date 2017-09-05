package com.caesar_84.sellhelper.service;

import com.caesar_84.sellhelper.domain.*;
import com.caesar_84.sellhelper.domain.auxclasses.Address;
import com.caesar_84.sellhelper.domain.auxclasses.OrderStatus;

import java.time.LocalDateTime;
import java.util.HashMap;

public class MockEntitiesForServiceTest {
    private MockEntitiesForServiceTest() {}
    private static HashMap<Good, Integer> savingGoodsMap = new HashMap<>();
    private static HashMap<Good, Integer> existentGoodsMap = new HashMap<>();
    private static LocalDateTime now;

    public static final Integer USER_ID = 2;

    //Client mocks -----------------------------------------------------------

    public static final Client CLIENT_MOCK_PRESAVED = new Client(
            "Клара",
            "Захаровна",
            "Заебенеева",
            "https://ok.ru/klarazaeb",
            2);
    public static final Client CLIENT_MOCK_EXPECTED = new Client(
            2,
            "Клара",
            "Захаровна",
            "Заебенеева",
            "https://ok.ru/klarazaeb", 2);
    public static final Client CLIENT_MOCK_EXPECTED_EXISTENT = new Client(
            1,
            "Васисуалий",
            "Модестович",
            "Пупкин",
            "skype: pupkin-vas-mod",
            2);

    //Address mocks -----------------------------------------------------------

    public static final Address ADDRESS_MOCK_PRESAVED = new Address(
            "Venezuela",
            "Aragua",
            "Maracay",
            "Barrio Libertador",
            2
    );
    public static final Address ADDRESS_MOCK_EXPECTED = new Address(
            2,
            "Venezuela",
            "Aragua",
            "Maracay",
            "Barrio Libertador",
            2
    );
    public static final Address ADDRESS_MOCK_EXPECTED_EXISTENT = new Address(
            1,
            "Россия",
            "Ставропольский край",
            "г.Пятигорск",
            "ул.Фучика 10, кв. 1",
            2
    );

    //Provider mocks -----------------------------------------------------------

    public static final GoodsProvider PROVIDER_MOCK_PRESAVED = new GoodsProvider(
            "ООО \"Филькины граммоты\"",
            "тел.: +7(793)111-11-11",
            USER_ID
    );
    public static final GoodsProvider PROVIDER_MOCK_EXPECTED = new GoodsProvider(
            2,
            "ООО \"Филькины граммоты\"",
            "тел.: +7(793)111-11-11",
            USER_ID
    );
    public static final GoodsProvider PROVIDER_MOCK_EXPECTED_EXISTENT = new GoodsProvider(
            1,
            "Рога & Копыта, Инк.",
            "тел.: +7(988)000-00-00",
            USER_ID
    );

    //Good mocks -----------------------------------------------------------

    public static final Good GOOD_MOCK_PRESAVED = new Good(
            "Фиолетовенькая глазовыколупывательница",
            "001235",
            "Глазовыколупывательница, фиолетовенькая, с полувыломанными ножками",
            USER_ID,
            PROVIDER_MOCK_EXPECTED_EXISTENT,
            10000
    );
    public static final Good GOOD_MOCK_EXPECTED = new Good(
            2,
            "Фиолетовенькая глазовыколупывательница",
            "001235",
            "Глазовыколупывательница, фиолетовенькая, с полувыломанными ножками",
            USER_ID,
            PROVIDER_MOCK_EXPECTED_EXISTENT,
            10000
    );
    public static final Good GOOD_MOCK_EXPECTED_EXISTENT = new Good(
            1,
            "Пятикрылый семихуй",
            "001234",
            "Пятикрылый семихуй, сиреневый, сборный",
            2,
            PROVIDER_MOCK_EXPECTED_EXISTENT,
            50000
    );

    //Stock mocks -----------------------------------------------------------

    public static final StockItem STOCK_ITEM_MOCK_PRESAVED = new StockItem(
            GOOD_MOCK_PRESAVED,
            20,
            USER_ID
    );
    public static final StockItem STOCK_ITEM_MOCK_EXPECTED = new StockItem(
            3,
            GOOD_MOCK_EXPECTED,
            20,
            USER_ID
    );
    public static final StockItem STOCK_ITEM_MOCK_EXPECTED_EXISTENT = new StockItem(
            1,
            GOOD_MOCK_EXPECTED_EXISTENT,
            10,
            2
    );

    //Order mocks -----------------------------------------------------------

    static {
        savingGoodsMap.put(GOOD_MOCK_EXPECTED_EXISTENT, 5);
        existentGoodsMap.put(GOOD_MOCK_EXPECTED_EXISTENT, 2);
        now = LocalDateTime.now();
    }

    public static final Order ORDER_MOCK_PRESAVED = new Order(
            CLIENT_MOCK_PRESAVED,
            ADDRESS_MOCK_PRESAVED,
            savingGoodsMap,
            USER_ID
    );
    public static final Order ORDER_MOCK_EXPECTED = new Order(
            4,
            CLIENT_MOCK_PRESAVED,
            ADDRESS_MOCK_PRESAVED,
            savingGoodsMap,
            USER_ID,
            now,
            now,
            OrderStatus.PENDING
    );
    public static final Order ORDER_MOCK_EXPECTED_EXISTENT = new Order(
            1,
            CLIENT_MOCK_EXPECTED_EXISTENT,
            ADDRESS_MOCK_EXPECTED_EXISTENT,
            existentGoodsMap,
            2,
            LocalDateTime.of(2017, 8, 23, 17, 18, 35, 464353),
            LocalDateTime.of(2017, 8, 23, 17, 18, 35, 464353),
            OrderStatus.PENDING
    );
}