sleep 200
if curl client-service-one/random | grep -q 'is equal to'; then
  echo "TEST PASSED!!!!"
  exit 0
else
  echo "TEST FAILED!!!!"
  exit 1
fi