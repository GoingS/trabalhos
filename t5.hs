addSufix :: String -> [String] -> [String]
addSufix str lis = [x ++ str | x <- lis]
----------------------------------------------------------------------------------
addSufixRec :: String -> [String] -> [String]
addSufixRec str lis
   |lis == [] = []
   |otherwise = ((head lis) ++ str) : (addSufixRec str (tail lis))
----------------------------------------------------------------------------------
countShorts :: [String] -> Int
countShorts lis
   |lis == [] = 0 
   |length (head lis) < 5 = 1 + (countShorts (tail lis))
   |otherwise = countShorts (tail lis)
----------------------------------------------------------------------------------
countShortsLisComp :: [String] -> Int
countShortsLisComp lis = length [x| x <- lis, length x < 5]
----------------------------------------------------------------------------------
ciclo :: Int -> [Int] -> [Int]
ciclo x lis 
   |x == 0 = []
   |otherwise = lis ++ (ciclo (x-1) lis)
----------------------------------------------------------------------------------
combine :: [Int] -> [String] -> [(Int, String)]
combine lis1 lis2
   |(lis1 == []) || (lis2 == []) = []
   |otherwise = ((head lis1), (head lis2)) : (combine (tail lis1) (tail lis2))
----------------------------------------------------------------------------------
aux :: Int -> [String] -> [(Int, String)]
aux x lis
   |lis == [] = []
   |otherwise = (x,(head lis)) : (aux (x+1) (tail lis)) 

numera :: [String] -> [(Int, String)]
numera lis = aux 1 lis 
----------------------------------------------------------------------------------
-- Exercício 8
-- a) Faz uma lista de tuplas caso um numero x de 1 a 5 seja par e x+1 seja impar.
-- b) Concatena as strings da lista 1 com a lista 2 duas a duas de todas as formas possíveis.
-- c) Cria uma string com as vogais contidas em "Paralelepipedo"
----------------------------------------------------------------------------------
crossProduct :: [a] -> [b] -> [(a,b)]
crossProduct lis1 lis2
   |length lis1 == 0 = []
   |otherwise = (pairWithAll (head lis1) lis2) ++ (crossProduct (tail lis1) lis2)

pairWithAll :: a -> [b] -> [(a,b)]
pairWithAll x lis 
   |length lis == 0 = []
   |otherwise = (x,head lis) : (pairWithAll x (tail lis))
----------------------------------------------------------------------------------
aux2 :: Int -> (Float, Float) -> [(Float, Float, Float, Float)]
aux2 n (x,y)
   |n == -1 = []
   |otherwise = (x, y, alt, comp) : (aux2 (n-1) (x+alt,y))
   where alt = 5.5 :: Float
         comp = 5.5 :: Float

genRects :: Int -> (Int, Int) -> [(Float, Float, Float, Float)]
genRects n (x,y) = aux2 n (fromIntegral x, fromIntegral y)
----------------------------------------------------------------------------------
func :: [(a,b)] -> ([a],[b])
func [] = ([],[])
func ((x,y):xys) = (x:xs, y:ys)
   where (xs,ys) = func xys
