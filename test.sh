test1=false
test2=false
test3=false

for (( i=1; i<=20; i++)) do
  if ! $test1 ; then
      if curl client-service-one:8181/random | grep -q 'is equal to'; then
        echo "client-service-one/random test passed"
        test1=true
      fi
  fi

  if ! $test2 ; then
      if curl client-service-one:8181/integral | grep -q 'is equal to'; then
        echo "client-service-one/integral test passed"
        test2=true
      fi
  fi

  if ! $test3 ; then
      if curl client-service-two:8282/generate-tasks | grep -q 'Executes'; then
          echo "client-service-two/generate-tasks test passed"
          test3=true
      fi
  fi

  if $test1 && $test2 && $test3; then
      echo "All tests passed"
      exit 0
  fi

  sleep 10
done

if ! $tes1 ; then
    echo "client-service-one/integral test failed"
fi

if ! $tes2 ; then
    echo "client-service-one/random test failed"
fi

if ! $tes3 ; then
    echo "client-service-two/generate-tasks test failed"
fi

exit 1