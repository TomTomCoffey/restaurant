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
    ('Bacon, Lettuce and Tomato', 'On white, wheat, rye bread or hard roll.', 5.00, 'temp.jpg', 6); -- 9
    ('Chicken Tender Sandwich', '', 5.50, 'temp.jpg', 6), -- 10
    ('Chicken Tender BLT Sandwich', '', 7.25, 'temp.jpg', 6), -- 11
    ('Buffalo Chicken Tender BLT Sandwich', '', 8.00, 'temp.jpg', 6), -- 12
    ('Buffalo Chicken Tender Sandwich', '', 6.25, 'temp.jpg', 6), -- 13
    ('Tuna Fish', 'On white, wheat, rye bread or hard roll.', 5.00, 6), -- 14
    ('Tuna Melt', 'On white, wheat, rye bread or hard roll.', 6.00, 6), -- 15
    ('Spicy Tuna Melt', 'Tuna melt with Buffalo Sauce, Bacon and Pickles', 7.25, 6), -- 16
    ('Fish Platter', 'Two pieces of fish with french fries and Tartar Sauce', 8.00, 6), -- 17
    ('Fish Sandwich', 'Lettuce, Tomato and Tartar Sauce', 5.25, 6), -- 18
    ('2 Eggs and Toast', 'Any style.', 4.50, 2), -- 19
    ('Western Omelette', 'Two eggs scrambled with ham, onions, peppers and toast.', 6.50, 2), -- 20
    ('Cheese Omlette', 'Two eggs scrambled with White American cheese and Toast', 5.50, 2), -- 21
    ('Ham & Cheese Omelette', 'Ham and cheese omelette served with a choice of bread: English muffin, hard roll, rye, wheat, or white', 6.50, 2), -- 22
    ('Veggie Omelette', 'Tomatoes, onions, and green pepper.', 6.50, 2) -- 23
    ('Dog & Cheese Omelette', 'Chopped hot dog and cheese.', 6.75, 2) -- 24
    ('Pancakes', '', 7.50, 2) -- 25
    ('French Toast', '', 7.50, 2) -- 26





    insert into modifiers(modifier_name, modifier_price) values
    ('English Miffin', 1.00),  -- 1
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
    ('Melted Cheddar Cheese', 1.00), -- 22
    ('White American Cheese Slice', 1.00) -- 23
    ('Blueberry', 0.00), -- 24
    ('Corn', 0.00), -- 25
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
    ('Sunny Side Up', 0.00); -- 45
    
    

    
  


    



    insert into submodifier(modifier_id, item_id) values
    (1, 1), --Western Sandwich
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
    (17, 13), --- Buffalo Chicken Tender Sandwich
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
    (8, 17),  --- Fish Platter
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
    (16, 26);



    


   

    