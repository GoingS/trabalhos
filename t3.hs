isEven :: Int -> Bool
isEven n = mod n 2 == 0 -- função mod devolve o resto da divisão inteira dos 2 numeros


somaquad :: Int -> Int -> Int
somaquad x y = x*x + y*y


doubleFirst :: [Int] -> Int
doubleFirst x = (head x)^2


hasEqHeads :: [Int] -> [Int] -> Bool
hasEqHeads x y = head x == head y


addSuf :: [[Char]] -> [[Char]]
addSuf x = map ("Mr "++) x


countSpace :: [Char] -> Int
countSpace x = length (filter (' ' ==) x)


calcEq :: [Float] -> [Float]
calcEq x = map (1+) (zipWith (+) (map (3*) (map (^2) x)) (map (/2) x))


idade :: [Int] -> [Int]
idade x = map (2015-) (filter (>1970) (map (2015-) x))


serie :: Double -> [Double] -> Double
serie m n = sum (map (/m) n)


charFound :: Char -> String -> Bool
charFound c s = (length (filter (==c) s)) > 0


htmlListItems :: [String] -> [String]
htmlListItems s =  map (++"</LI>") (map ("<LI>"++)  s)

-- Exercicio 12
-- takeWhile (<'t') "abacaxi"

-- Exercicio 13

firstNameEndsA :: String -> Bool
firstNameEndsA s = last (takeWhile (/=' ') s) == 'a'

filterGuests :: [String] -> [String]
filterGuests s = filter (firstNameEndsA) s 


