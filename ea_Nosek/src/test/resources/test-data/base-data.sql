INSERT INTO public.manufacturer (id,name, country_of_origin, year_established)
VALUES (1,'Toyota', 'Japan', 1937);

INSERT INTO public.manufacturer (id ,name, country_of_origin, year_established)
VALUES (2,'Ford', 'USA', 1903);


INSERT INTO public.fuel_data (id, year, make, model, class, drive, transmission, engine_index, engine_descriptor, engine_cylinders, engine_displacement, turbocharger, fuel_type, city_mpg_ft1, highway_mpg_ft1, unrounded_city_mpg_ft1, unrounded_highway_mpg_ft1, combined_mpg, annual_fuel_cost_ft1, annual_consumption_in_barrels_ft1, tailpipe_co2_in_grams_mile_ft1, manufacturer_id)
VALUES (1, 2020, 'Toyota', 'Camry', 'Sedan', 'FWD', 'Automatic', 1, 'V6', 6.0, 3.5, 'No', 'Gasoline', 28, 39, 28.5, 38.7, 32, 1000.00, 15.00, 230.00, 1);
INSERT INTO public.fuel_data (id, year, make, model, class, drive, transmission, engine_index, engine_descriptor, engine_cylinders, engine_displacement, turbocharger, fuel_type, city_mpg_ft1, highway_mpg_ft1, unrounded_city_mpg_ft1, unrounded_highway_mpg_ft1, combined_mpg, annual_fuel_cost_ft1, annual_consumption_in_barrels_ft1, tailpipe_co2_in_grams_mile_ft1, manufacturer_id)
VALUES (2, 2019, 'Ford', 'F-150', 'Truck', '4WD', 'Automatic', 1, 'V8', 8.0, 5.0, 'Yes', 'Gasoline', 20, 26, 20.2, 25.8, 22, 1500.00, 20.00, 300.00, 2);
INSERT INTO public.fuel_data (id, year, make, model, class, drive, transmission, engine_index, engine_descriptor, engine_cylinders, engine_displacement, turbocharger, fuel_type, city_mpg_ft1, highway_mpg_ft1, unrounded_city_mpg_ft1, unrounded_highway_mpg_ft1, combined_mpg, annual_fuel_cost_ft1, annual_consumption_in_barrels_ft1, tailpipe_co2_in_grams_mile_ft1, manufacturer_id)
VALUES (3, 2019, 'Ford', 'Focus', 'Truck', '4WD', 'Automatic', 1, 'V8', 8.0, 5.0, 'Yes', 'Gasoline', 20, 26, 20.2, 25.8, 22, 1500.00, 20.00, 100.00, 2);
INSERT INTO public.fuel_data (id, year, make, model, class, drive, transmission, engine_index, engine_descriptor, engine_cylinders, engine_displacement, turbocharger, fuel_type, city_mpg_ft1, highway_mpg_ft1, unrounded_city_mpg_ft1, unrounded_highway_mpg_ft1, combined_mpg, annual_fuel_cost_ft1, annual_consumption_in_barrels_ft1, tailpipe_co2_in_grams_mile_ft1, manufacturer_id)
VALUES (4, 2020, 'Toyota', 'Camry', 'Sedan', 'FWD', 'Automatic', 1, 'V6', 6.0, 3.5, 'No', 'Gasoline', 28, 39, 28.5, 38.7, 32, 1500.00, 15.00, 200.00, 1);