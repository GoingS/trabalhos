import Text.Printf
import System.Environment

type Point = (Int, Int)    
type Size = (Int, Int)
type Hsl = (Int,Int,Int)        
type Rect = (Point, Size, Hsl)
type Pallete = (Point, Size, (Int,Int), Int)

pos :: (Eq a) => a -> [a] -> Int                                   -- Função simples para retornar a posição de um elemento
pos x lis 
   |x == (head lis) = 0
   |otherwise = 1 + (pos x (tail lis))

dist :: Point -> Point -> Int                                      -- Distancia entre dois pontos no plano
dist (x1,y1) (x2,y2) = floor $ sqrt $ fromIntegral (fromIntegral (x2-x1)^2 + (y2-y1)^2)

light :: Int -> Int -> Int                                         -- Devolve um valor de 30 a 100 em relação a distância de m a 0
light m dist = 100-(70*dist)`div`m

-------------------------------------------------------------------------------------
cosVect :: (Int,Int) -> Float                                       -- Já simplificada, retorna o cosseno do
cosVect (x,y)                                                       -- ângulo formado com o vetor dado e o vetor (1,0)
   |x == 0 && y == 0 = 1                                            -- Angulo formado com o vetor nulo, mas não interessa nesse caso
   |otherwise = (fromIntegral x / sqrt (fromIntegral (x^2+y^2)))   
 
angVect :: (Int, Int) -> Int -> Int
angVect (x,y) rad
    |y <= rad = floor $  acos(cosVect(x-rad,y-rad)) * 360 / (2*pi)              -- Para angulos de 0 a 180
    |otherwise = floor $ (2*pi - (acos (cosVect (x-rad,y-rad)))) * 360 / (2*pi) -- de 180~360, já que o um cos pode ter 2 angulos
-------------------------------------------------------------------------------------

modx :: Float -> Float -> Float                                    -- Mod para números reais
modx x y
   |x >= y = modx (x - y) y
   |otherwise = x

--------------------------------------------------------------------------------------
hslToRgb :: (Float, Float, Float) -> (Int, Int, Int)
hslToRgb (h, s, l) = (floor $ 255 * hueToRgb (m1,m2, h'+1/3),
                      floor $ 255 * hueToRgb (m1,m2, h')    ,  
                      floor $ 255 * hueToRgb (m1,m2, h'-1/3) )
   where
      h' = abs (modx h 1)       -- Caso o ângulo seja maior de 360, ele "dá a volta".
      m2 = if l<=0.5 
           then l*(s+1)
           else l+s-(l*s)                                           -- Fórmula extraída do site no material de apoio
      m1 = l*2-m2                                                   -- http://www.w3.org/TR/css3-color/#hsl-color

hueToRgb :: (Float, Float, Float) -> (Float)
hueToRgb (m1,m2,h)
   |h'* 6 < 1 = m1+(m2-m1)*h'*6
   |h'* 2 < 1 = m2
   |h'* 3 < 2 = m1+(m2-m1)*(2/3 - h') * 6
   |otherwise = m1
      where h' = if h < 0 then h+1 else (if h > 1 then h-1 else h)
---------------------------------------------------------------------------------------



writeRect :: Rect -> String                                        -- Transforma um tipo Rect em uma string em código svg
writeRect ((x,y),(rectW,rectH),(h,s,l)) 
           = printf "    <rect x=\"%d\" y=\"%d\" width=\"%d\" height=\"%d\" style=\"fill:rgb(%d,%d,%d)\"/>  (%d,%d,%d)\n" x y rectW rectH r g b h s l
                  where (r,g,b) = hslToRgb ((fromIntegral h)/360, (fromIntegral s)/100,(fromIntegral l)/100)

                                                                   -- Formato extraído de http://www.w3schools.com/svg/svg_rect.asp


writeRects :: [Rect] -> String                                     -- Transforma uma lista do tipo rect em código svg
writeRects lis =  concat (map (writeRect) lis)




createPallete' :: Pallete -> (String,Size)
createPallete' ((xp,yp), (w,h), (c,l), hue) =  
      ((writeRect ((xp,yp),(w'-xp,h'-yp),(60,80,7))) ++            -- retângulo cinza que fica atrás da paleta
      (writeRects [((x,y),(rectW,rectH),(hue, saturations !! (pos x posX), lights !! (pos y posY))) | x <- posX , y <- posY]),(w,h))
                       
                       where w' = last posX + rectW + space                    -- w para que não sobre espaços a direita
                             h' = last posY + rectH + space                    -- h para que não sobre espaços a baixo
                             rectW = (w - (space*c + space)) `div` c                -- largura dos retângulos
                             rectH = (h - (space*l + space)) `div` l                -- altura  
                             space = ((w+h) `div` (c+l)) `div` 15                   -- espaço (baseado no tamanho da matriz)
                             posX = [xp+space,xp+rectW+2*space..xp+w-rectW]         -- posições x onde retângulos vão ficar
                             posY = [yp+space,yp+rectH+2*space..yp+h-rectH]         --   ""     y     ""
                             saturations = [100, 100 - (100 `div` (c-1))..0]        -- contem as porcentages sat. 
                             lights = [100,100 - (100 `div` (l-1))..0]              -- contem lights que vão ser usadas e são escolhidas pela
                                                                                    -- posição da coluna e linha do retângulo sendo escrito



createPallete2 :: Int -> Int -> Int -> (String, Size)              -- Tamanho do círculo -> hue -> Saturação
createPallete2 l hue sat = ((writeRects lis), (l',l'))
       where squareS = l `div` 100 -- square size
             l' = squareS * 100  
             lis = [((x,y),(squareS,squareS),(hue,sat,light m (dist (x,y) (m,m)))) | x <- pos, y <-pos, m >= (dist (m,m) (x,y))]
             pos = [squareS, 2*squareS .. l'-squareS]
             m = l `div` 2

createPallete2' :: Int -> Int -> (String, Size)              -- Tamanho do círculo -> hue -> Saturação
createPallete2' l sat = ((writeRects lis), (l',l'))
       where squareS = l `div` l -- square size
             l' = squareS * l  
             lis = [((x,y),(squareS,squareS),(angVect (x,y) m,sat,light m (dist (x,y) (m,m)))) | x <- pos, y <-pos, m >= (dist (m,m) (x,y))]
             pos = [squareS, 2*squareS .. l'-squareS]
             m = l `div` 2
           


createPallete3 :: Size -> Int -> Int -> (Int,Int) -> (String, Size) -- Tamanho da paleta -> Colunas -> Linha -> (hue1, hue2)
createPallete3 (w,h) c l (h1,h2) = ((writeRect ((0,0),(w,h),(60,80,7)))++(writeRects lis), (w',h'))
       where w' = last posX + rectW + space 
             h' = last posY + rectH + space 
             rectW = (w - (space*c + space)) `div` c
             rectH = (h - (space*l + space)) `div` l 
             space = ((w+h) `div` (c+l)) `div` 15
             posX = [space,rectW + space*2 .. w-rectW]
             posY = [space,rectH + space*2 .. h-rectH]
             distOrigEnd = dist (0,0) (l,c)
             hue = [h1,h1+(h2-h1) `div` dist (0,0) (l,c)..h2]
             lis = [((x,y),(rectW,rectH),(hue !! (dist (0,0) (pos x posX,pos y posY)),50,50)) | x <- posX, y <-posY]
             

--  CreatePallete2 exemplo: createSvg $ createPallete2 500 80 40
-- CreatePallete2' exemplo: createSvg $ createPallete2' 200 60    // (Tamanho e saturação), valores maiores que 200 podem demorar alguns segundos
--  CreatePallete3 exemplo: createSvg $ createPallete3 (500,500) 10 8 (200,350)


createSvg :: (String, Size) -> IO ()          
createSvg (str,(w,h)) = writeFile "colours.svg" 
       ((printf "<svg width= \"%d\" height=\"%d\" xmlns=\"http://www.w3.org/2000/svg\">\n" w h) ++
       (str) ++    
       ("</svg>"))

-- Códigos no início e fim foram pegos de um editor online svg http://svg-edit.googlecode.com/svn-history/r1771/trunk/editor/svg-editor.html
