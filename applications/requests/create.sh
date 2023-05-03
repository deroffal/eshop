
#local
BASE_URI='http://localhost:8081'
#port-forward
BASE_URI='http://localhost:8080'

# Create a baguette
curl --location --request POST "$BASE_URI/products" \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Baguette traditionnelle",
    "description": "La baguette traditionnelle française, croustillante à l'\''extérieur et moelleuse à l'\''intérieur",
    "price": 0.9
}'


# Create a croissant
curl --location --request POST "$BASE_URI/products" \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Croissant",
    "description": "Le célèbre croissant français, feuilleté et doré à souhait",
    "price": 1.2
}'

# Create a bottle of champagne
curl --location --request POST "$BASE_URI/products" \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Champagne brut",
    "description": "Le roi des vins, effervescent et fruité, parfait pour les occasions spéciales",
    "price": 35.99
}'

# Create a wheel of camembert cheese
curl --location --request POST "$BASE_URI/products" \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Camembert de Normandie",
    "description": "Un fromage emblématique de la Normandie, à la pâte molle et au goût prononcé",
    "price": 6.5
}'

# Create a jar of Dijon mustard
curl --location --request POST "$BASE_URI/products" \
--header 'Content-Type: application/json' \
--data-raw '{
    "name": "Moutarde de Dijon",
    "description": "La moutarde de Dijon, fabriquée à partir de graines de moutarde brune et vinaigre de vin blanc",
    "price": 2.99
}'
