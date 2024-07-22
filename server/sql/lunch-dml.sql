use lunch;

  insert into app_role(role_name) values
  ('ROLE_USER'),
  ('ROLE_ADMIN');


  insert into category(category_name) values
    ('Hot Dogs, Burgers, French Fries'),
    ('Breakfast Platter'),
    ('Breakfast Sandwiches'),
    ('Beverages'),
    ('Jars of Sauce'),
    ('Sandwiches');

  
    insert into item(item_title, item_description, item_price, item_photo, category_id) values
    ('Western Sandwich', 'One egg scrambled with ham, onions and peppers. On white, wheat or rye bread.', 5.25, 'temp.jpg', 3), -- 1
    ('Toast', 'With butter.', 1.50, 'temp.jpg', 3), -- 2
    ('English Muffin', 'With butter.', 1.50, 'temp.jpg', 3), -- 3
    ('Hard Roll', 'With butter.', 1.50, 'temp.jpg', 3), -- 4
    ('Egg Sandwich', 'One egg on white, wheat or rye bread.', 2.50, 'temp.jpg', 3), -- 5
    ('Pastry', 'Blueberry Muffin, Corn Muffin', 1.75, 'temp.jpg', 3), -- 6
    ('Tom Tom Patty Melt', 'Chopped Hamburger, Chopped Bacon, Texas Sauce and Onions wedged between a grill cheese', 5.50, 'temp.jpg', 6), -- 7
    ('Chicken Tender Buffalo BLT Wrap', 'Crispy chicken tenders, bacon, lettuce, tomato, and buffalo sauce wrapped in a tortilla.', 7.50, 'temp.jpg', 6), -- 8
    ('Bacon, Lettuce and Tomato', 'On white, wheat, rye bread or hard roll.', 5.00, 'temp.jpg', 6), -- 9
    ('Chicken Tender Sandwich', '', 5.50, 'temp.jpg', 6), -- 10
    ('Chicken Tender BLT Sandwich', '', 7.25, 'temp.jpg', 6), -- 11
    ('Buffalo Chicken Tender BLT Sandwich', '', 8.00, 'temp.jpg', 6), -- 12
    ('Buffalo Chicken Tender Sandwich', '', 6.25, 'temp.jpg', 6), -- 13
    ('Tuna Fish', 'On white, wheat, rye bread or hard roll.', 5.00, 'temp.jpg', 6), -- 14
    ('Tuna Melt', 'On white, wheat, rye bread or hard roll.', 6.00, 'temp.jpg', 6), -- 15
    ('Spicy Tuna Melt', 'Tuna melt with Buffalo Sauce, Bacon and Pickles', 7.25, 'temp.jpg', 6), -- 16
    ('Fish Platter', 'Two pieces of fish with french fries and Tartar Sauce', 8.00,'temp.jpg', 6), -- 17
    ('Fish Sandwich', 'Lettuce, Tomato and Tartar Sauce', 5.25,'temp.jpg', 6), -- 18
    ('2 Eggs and Toast', 'Any style.', 4.50,'temp.jpg', 2), -- 19
    ('Western Omelette', 'Two eggs scrambled with ham, onions, peppers and toast.', 6.50,'temp.jpg', 2), -- 20
    ('Cheese Omlette', 'Two eggs scrambled with White American cheese and Toast', 5.50,'temp.jpg', 2), -- 21
    ('Ham & Cheese Omelette', 'Ham and cheese omelette served with a choice of bread: English muffin, hard roll, rye, wheat, or white', 6.50,'temp.jpg', 2), -- 22
    ('Veggie Omelette', 'Tomatoes, onions, and green pepper.', 6.50,'temp.jpg', 2), -- 23
    ('Dog & Cheese Omelette', 'Chopped hot dog and cheese.', 6.75,'temp.jpg', 2), -- 24
    ('Pancakes', '', 8.50,'temp.jpg', 2), -- 25
    ('French Toast', '', 8.50,'temp.jpg', 2), -- 26
    ('Side of Home Fries', '',  3.00,'temp.jpg', 2), -- 27
    ('Side of Grits', '', 3.00,'temp.jpg', 2), -- 28
    ('Texas Hot Weiners', 'Pork and Beef hot dog. Please specify what you want on the hot dog otherwise it will be plain', 2.75,'temp.jpg', 1), -- 29
    ('Hot Dog No Roll', 'Pork and Beef hot dog. Please specify what you want on the hot dog otherwise it will be plain', 2.00,'temp.jpg', 1), -- 30
    ('All Beef Texas Weiners', 'Please specify what you want on the hot dog otherwise it will be plain', 3.00,'temp.jpg', 1), -- 31
    ('Hamburger', '', 3.25,'temp.jpg', 1), -- 32
    ('Cheeseburger', '', 3.75,'temp.jpg', 1), -- 33
    ('Double Hamburger', '', 6.00,'temp.jpg', 1), -- 34
    ('Double Cheeseburger', '', 7.00,'temp.jpg', 1), -- 35
    ('Side of Sauce', '4 oz cup of sauce', 1.25,'temp.jpg', 1), -- 36
    ('French Fries', '', 2.75,'temp.jpg', 1), -- 37
    ('Onion Rings', '', 3.00,'temp.jpg', 1), -- 38
    ('Buffalo Burger', '1/4 Burger mixed with our Buffalo sauce, Bacon, Lettuce and Tomato with Ranch Dressing on a hard roll', 8.00,'temp.jpg', 1), -- 39
    ('Can Soda', '', 2.00,'temp.jpg', 4), -- 40
    ('Coffee', 'Please specify how you take your coffee. Milk, Half and Half, How many sugars, Splenda, Equal or Sweet and Low', 2.00,'temp.jpg', 4), -- 41
    ('Hot Tea', 'Please specify how you take your tea. Milk, Half and Half, How many sugars, Splenda, Equal or Sweet and Low', 2.00,'temp.jpg', 4), -- 42
    ('Bottled Water', '', 2.75,'temp.jpg', 4), -- 43
    ('Small Jar 16 ounce', '', 9.00,'temp.jpg', 5), -- 44
    ('Large Jar 25 ounce', '', 13.00,'temp.jpg', 5); -- 45






    insert into modifiers(modifier_name, modifier_price) values
    ('English Muffin', 1.00),  -- 1
    ('Hard Roll', 1.00), -- 2
    ('Toasted', 0.00), -- 3 
    ('Not Toasted', 0.00), -- 4
    ('Rye', 0.00), -- 5
    ('White', 0.00), -- 6
    ('Wheat', 0.00), -- 7
    ('Extra Egg', 1.00), -- 8
    ('2 Extra Eggs', 2.00), -- 9
    ('3 Extra Eggs', 3.00), -- 10
    ('4 Extra Eggs', 4.00), -- 11
    ('5 Extra Eggs', 5.00), -- 12
    ('Home Fries in Sandwich', 1.00), -- 13
    ('Sausage', 2.50), -- 14
    ('Bacon', 2.50),  -- 15
    ('Ham', 2.50), -- 16
    ('Lettuce', 0.50), -- 17
    ('Tomato', 0.50), -- 18
    ('Texas Sauce', 0.50), -- 19
    ('Jelly', 0.50), -- 20
    ('Egg White', 1.00), -- 21
    ('Melted Cheddar Cheese', 1.25), -- 22
    ('White American Cheese Slice', 1.25), -- 23
    ('Blueberry Muffin', 0.00), -- 24
    ('Corn Muffin', 0.00), -- 25
    ('No Bacon', 0.00), -- 26
    ('No Onions', 0.00), -- 27
    ('No Sauce', 0.00), -- 28
    ('***** SUB BBQ for Buffalo *****', 0.00), -- 29
    ('Extra Bacon', 2.00), -- 30
    ('Texas Sauce', 1.50), -- 31
    ('Melted Cheddar Cheese', 1.00), -- 32
    ('White American Cheese Slice', 1.00), -- 33
    ('Mayonnaise', 0.00), -- 34
    ('BBQ', 0.00), -- 35
    ('Honey Mustard', 0.00), -- 36
    ('Ranch', 0.00), -- 37
    ('Onions', 0.00), -- 38
    ('Grits', 2.00), -- 39
    ('Home Fries', 2.00), -- 40
    ('Over Easy', 0.00), -- 41
    ('Over Medium', 0.00), -- 42
    ('Over Hard', 0.00), -- 43
    ('Scrambled', 0.00), -- 44
    ('Sunny Side Up', 0.00), -- 45
    ('Bacon Bits', 1.25), -- 46
    ('Texas Sauce', 0.00), -- 47
    ('Mustard', 0.00), -- 48
    ('Sauerkraut', 0.00), -- 49
    ('Ketchup', 0.00), -- 50
    ('Heavy Onions', 0.50), -- 51
    ('Heavy Texas Sauce', 0.50), -- 52
    ('Relish', 0.00), -- 53
    ('Split Well Done', 0.00), -- 54
    ('Bacon', 1.25), -- 55
    ('Extra Cheese', 1.00); -- 56

    
    


    insert into submodifier(modifier_id, item_id) values
    (1, 1), -- Western Sandwich
    (2, 1),
    (3, 1),
    (4, 1),
    (5, 1),
    (6, 1),
    (7, 1),
    (8, 1),
    (9, 1),
    (10, 1),
    (11, 1),
    (12, 1),
    (13, 1),
    (14, 1),
    (15, 1),
    (16, 1),
    (17, 1),
    (18, 1),
    (19, 1), 
    (22, 1),
    (23, 1),
    (20, 2), -- Toast
    (20, 3), -- English Muffin
    (20, 4), -- Hard Roll
    (1, 5), -- Egg Sandwich
    (2, 5),
    (3, 5),
    (4, 5),
    (5, 5),
    (6, 5),
    (7, 5),
    (8, 5),
    (9, 5),
    (10, 5),
    (11, 5),
    (12, 5),
    (13, 5),
    (14, 5),
    (15, 5),
    (16, 5),
    (17, 5),
    (18, 5),
    (19, 5),
    (21, 5),
    (22, 5),
    (23, 5),
    (24, 6), -- Pastry
    (25, 6),
    (17, 7), -- Tom Tom Patty Melt
    (18, 7),
    (26, 7),
    (27, 7),
    (28, 7),
    (8, 7),
    (9, 7),
    (10, 7),
    (11, 7),
    (12, 7),
    (29, 8), -- Chicken Tender Buffalo BLT Wrap
    (30, 8),
    (31, 8),
    (32, 8),
    (33, 8),
    (8, 8),
    (9, 8),
    (10, 8),
    (11, 8),
    (12, 8),
    (2, 9), -- BLT
    (5, 9),
    (6, 9),
    (7, 9),
    (34, 9),
    (8, 9),
    (9, 9),
    (10, 9),
    (11, 9),
    (12, 9),
    (17, 10), -- Chicken Tender Sandwich
    (18, 10),
    (32, 10),
    (33, 10),
    (34, 10),
    (35, 10),
    (36, 10),
    (37, 10),
    (8, 10),
    (9, 10),
    (10, 10),
    (11, 10),
    (12, 10),
    (32, 11), -- Chicken Tender BLT
    (33, 11),
    (34, 11),
    (35, 11),
    (36, 11),
    (37, 11),
    (8, 11),
    (9, 11),
    (10, 11),
    (11, 11),
    (12, 11),
    (8, 12), -- Buffalo Chicken BLT
    (9, 12),
    (10, 12),
    (11, 12),
    (12, 12),    
    (17, 13), -- Buffalo Chicken Tender Sandwich
    (18, 13),
    (32, 13),
    (33, 13),
    (34, 13),
    (35, 13),
    (36, 13),
    (37, 13),
    (8, 13),
    (9, 13),
    (10, 13),
    (11, 13),
    (12, 13),
    (1, 14), -- Tuna Fish
    (2, 14),
    (3, 14),
    (4, 14),
    (5, 14),
    (6, 14),
    (7, 14),
    (17, 14),
    (18, 14),
    (38, 14),
    (8, 14),
    (9, 14),
    (10, 14),
    (11, 14),
    (12, 14),
    (14, 14),
    (15, 14),
    (16, 14),
    (1, 15), -- Tuna Melt
    (2, 15),
    (3, 15),
    (4, 15),
    (5, 15),
    (6, 15),
    (7, 15),
    (17, 15),
    (18, 15),
    (38, 15),
    (8, 15),
    (9, 15),
    (10, 15),
    (11, 15),
    (12, 15),
    (14, 15),
    (15, 15),
    (16, 15),
    (8, 16), -- Spicy Tuna Melt
    (9, 16),
    (10, 16),
    (11, 16),
    (12, 16),
    (8, 17),  -- Fish Platter
    (9, 17),
    (10, 17),
    (11, 17),
    (12, 17),
    (2, 18), -- Fish Sandwich
    (3, 18),
    (4, 18),
    (5, 18),
    (6, 18),
    (7, 18),
    (8, 18),
    (9, 18),
    (10, 18),
    (11, 18),
    (12, 18),
    (1, 19), -- 2 Eggs and Toast
    (2, 19),
    (5, 19),
    (6, 19),
    (7, 19),
    (8, 19),
    (9, 19),
    (10, 19),
    (11, 19),
    (12, 19),
    (14, 19),
    (15, 19),
    (16, 19),
    (39, 19),
    (40, 19),
    (41, 19),
    (42, 19),
    (43, 19),
    (44, 19),
    (1, 20), -- Western Omelette
    (2, 20),
    (5, 20),
    (6, 20),
    (7, 20),
    (8, 20),
    (9, 20),
    (10, 20),
    (11, 20),
    (12, 20),
    (14, 20),
    (15, 20),
    (16, 20),
    (39, 20),
    (40, 20),
    (41, 20),
    (42, 20),
    (43, 20),
    (44, 20),
    (32, 20),
    (33, 20),
    (1, 21), -- Cheese Omlette
    (2, 21),
    (5, 21),
    (6, 21),
    (7, 21),
    (8, 21),
    (9, 21),
    (10, 21),
    (11, 21),
    (12, 21),
    (14, 21),
    (15, 21),
    (16, 21),
    (39, 21),
    (40, 21),
    (41, 21),
    (42, 21),
    (43, 21),
    (44, 21),
    (22, 21),
    (23, 21),
    (1, 22), -- Ham & Cheese Omlette
    (2, 22),
    (5, 22),
    (6, 22),
    (7, 22),
    (8, 22),
    (9, 22),
    (10, 22),
    (11, 22),
    (12, 22),
    (14, 22),
    (15, 22),
    (16, 22),
    (39, 22),
    (40, 22),
    (41, 22),
    (42, 22),
    (43, 22),
    (44, 22),
    (22, 22),
    (23, 22),
    (1, 23), -- Veggie Omelette
    (2, 23),
    (5, 23),
    (6, 23),
    (7, 23),
    (8, 23),
    (9, 23),
    (10, 23),
    (11, 23),
    (12, 23),
    (14, 23),
    (15, 23),
    (16, 23),
    (39, 23),
    (40, 23),
    (41, 23),
    (42, 23),
    (43, 23),
    (44, 23),
    (1, 24), -- Dog & Cheese Omelette
    (2, 24),
    (5, 24),
    (6, 24),
    (7, 24),
    (8, 24),
    (9, 24),
    (10, 24),
    (11, 24),
    (12, 24),
    (14, 24),
    (15, 24),
    (16, 24),
    (39, 24),
    (40, 24),
    (41, 24),
    (42, 24),
    (43, 24),
    (44, 24),
    (22, 24),
    (23, 24),
    (14, 25), -- Pancaakes
    (15, 25),
    (16, 25),
    (14, 26), -- French Toast
    (15, 26),
    (16, 26),
    (19, 27), -- Side of Home Fries
    (22, 27),
    (23, 27),
    (46, 27),
    (19, 28), -- Side of Grits
    (22, 28),
    (23, 28),
    (46, 28),
    (47, 29), -- Texas Hot Weiner
    (48, 29),
    (38, 29),
    (32, 29),
    (33, 29),
    (46, 29),
    (49, 29),
    (50, 29),
    (51, 29),
    (52, 29),
    (53, 29),
    (54, 29),
    (47, 30), -- Hot Dog No Roll
    (48, 30),
    (38, 30),
    (32, 30),
    (33, 30),
    (46, 30),
    (49, 30),
    (50, 30),
    (51, 30),
    (52, 30),
    (53, 30),
    (54, 30),
    (47, 31), -- All Beef Hot Dog
    (48, 31),
    (38, 31),
    (32, 31),
    (33, 31),
    (46, 31),
    (49, 31),
    (50, 31),
    (51, 31),
    (52, 31),
    (53, 31),
    (54, 31),
    (2, 32), -- Hamburger
    (47, 32),
    (38, 32),
    (17, 32),
    (18, 32),
    (34, 32),
    (48, 32),
    (49, 52),
    (50, 32),
    (51, 32),
    (52, 32),
    (53, 32),
    (55, 32),
    (2, 33), -- Cheeseburger
    (47, 33),
    (38, 33),
    (17, 33),
    (18, 33),
    (34, 33),
    (48, 33),
    (49, 33),
    (50, 33),
    (51, 33),
    (52, 33),
    (53, 33),
    (55, 33),
    (56, 33),
    (2, 34), -- Double Hamburger
    (47, 34),
    (38, 34),
    (17, 34),
    (18, 34),
    (34, 34),
    (48, 34),
    (49, 34),
    (50, 34),
    (51, 34),
    (52, 34),
    (53, 34),
    (55, 34),
    (2, 35), -- Double Cheeseburger
    (47, 35),
    (38, 35),
    (17, 35),
    (18, 35),
    (34, 35),
    (48, 35),
    (49, 35),
    (50, 35),
    (51, 35),
    (52, 35),
    (53, 35),
    (55, 35),
    (56, 35),
    (19, 37), -- French Fries
    (22, 37),
    (23, 37),
    (46, 37),
    (19, 38), -- Onion Rings
    (22, 38),
    (23, 38),
    (46, 38);
    